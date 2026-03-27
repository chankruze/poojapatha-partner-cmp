package geekofia.poojapatha.partner.cmp.core.network.interceptor

import geekofia.poojapatha.partner.cmp.core.data.local.AppPreferences
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Detects 401 Unauthorized responses, clears the stored auth token,
 * and emits an event so the UI layer can redirect to the auth screen.
 *
 * Collect [TokenExpiredInterceptor.tokenExpiredEvents] in the root
 * NavHost or Activity to react to forced logouts.
 */
@Singleton
class TokenExpiredInterceptor @Inject constructor(
    private val preferences: AppPreferences,
) : Interceptor {

    private val _tokenExpiredEvents = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val tokenExpiredEvents: SharedFlow<Unit> = _tokenExpiredEvents.asSharedFlow()

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == 401) {
            runBlocking { preferences.clearAuthToken() }
            _tokenExpiredEvents.tryEmit(Unit)
        }
        return response
    }
}
