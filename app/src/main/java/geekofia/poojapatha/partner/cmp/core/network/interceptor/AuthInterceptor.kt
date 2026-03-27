package geekofia.poojapatha.partner.cmp.core.network.interceptor

import geekofia.poojapatha.partner.cmp.core.data.local.AppPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Attaches the stored Bearer token to every outgoing request.
 * Requests to the auth endpoints (send-otp, verify-otp) skip this header,
 * but including it is harmless — the server ignores unknown tokens.
 */
class AuthInterceptor @Inject constructor(
    private val preferences: AppPreferences,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { preferences.authToken.first() }
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}
