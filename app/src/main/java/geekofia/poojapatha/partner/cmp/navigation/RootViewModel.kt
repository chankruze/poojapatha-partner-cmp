package geekofia.poojapatha.partner.cmp.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import geekofia.poojapatha.partner.cmp.core.data.local.AppPreferences
import geekofia.poojapatha.partner.cmp.core.network.interceptor.TokenExpiredInterceptor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    preferences: AppPreferences,
    tokenExpiredInterceptor: TokenExpiredInterceptor,
) : ViewModel() {

    sealed interface AuthState {
        data object Loading : AuthState
        data object Authenticated : AuthState
        data object Unauthenticated : AuthState
    }

    val authState: StateFlow<AuthState> = preferences.authToken
        .map { token ->
            if (token != null) AuthState.Authenticated else AuthState.Unauthenticated
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = AuthState.Loading,
        )

    /** Emits whenever a 401 forces a logout — collect in NavHost to redirect. */
    val tokenExpiredEvents: SharedFlow<Unit> = tokenExpiredInterceptor.tokenExpiredEvents
}
