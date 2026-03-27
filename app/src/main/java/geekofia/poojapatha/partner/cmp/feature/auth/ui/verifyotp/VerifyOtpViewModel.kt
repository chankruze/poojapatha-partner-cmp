package geekofia.poojapatha.partner.cmp.feature.auth.ui.verifyotp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import geekofia.poojapatha.partner.cmp.feature.auth.domain.repository.AuthRepository
import geekofia.poojapatha.partner.cmp.navigation.VerifyOtpRoute
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class VerifyOtpUiState(
    val otp: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
) {
    val isComplete: Boolean get() = otp.length == 6
}

sealed interface VerifyOtpEvent {
    data object NavigateToMain : VerifyOtpEvent
}

@HiltViewModel
class VerifyOtpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val identifier: String = savedStateHandle.toRoute<VerifyOtpRoute>().identifier

    var state by mutableStateOf(VerifyOtpUiState())
        private set

    var resendCountdown by mutableIntStateOf(RESEND_DELAY_SECONDS)
        private set

    private val _events = Channel<VerifyOtpEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private var countdownJob: Job? = null

    init {
        startResendCountdown()
    }

    fun onOtpChange(value: String) {
        if (value.length > 6 || !value.all(Char::isDigit)) return
        state = state.copy(otp = value, error = null)
        if (value.length == 6) verifyOtp()
    }

    fun verifyOtp() {
        if (!state.isComplete || state.isLoading) return
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            authRepository.verifyOtp(identifier, state.otp)
                .onSuccess { _events.send(VerifyOtpEvent.NavigateToMain) }
                .onFailure { throwable ->
                    state = state.copy(otp = "", error = throwable.message ?: "Invalid OTP")
                }
            state = state.copy(isLoading = false)
        }
    }

    fun resendOtp() {
        viewModelScope.launch {
            authRepository.sendOtp(identifier)
            state = state.copy(otp = "", error = null)
            startResendCountdown()
        }
    }

    private fun startResendCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            for (i in RESEND_DELAY_SECONDS downTo 0) {
                resendCountdown = i
                if (i > 0) delay(1_000)
            }
        }
    }

    private companion object {
        const val RESEND_DELAY_SECONDS = 30
    }
}
