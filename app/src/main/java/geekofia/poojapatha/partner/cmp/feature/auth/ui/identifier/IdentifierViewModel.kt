package geekofia.poojapatha.partner.cmp.feature.auth.ui.identifier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import geekofia.poojapatha.partner.cmp.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class IdentifierUiState(
    val identifier: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
) {
    val canSubmit: Boolean get() = identifier.isNotBlank() && !isLoading
}

sealed interface IdentifierEvent {
    data class NavigateToVerifyOtp(val identifier: String) : IdentifierEvent
}

@HiltViewModel
class IdentifierViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var state by mutableStateOf(IdentifierUiState())
        private set

    private val _events = Channel<IdentifierEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onIdentifierChange(value: String) {
        state = state.copy(identifier = value, error = null)
    }

    fun sendOtp() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            authRepository.sendOtp(state.identifier)
                .onSuccess {
                    _events.send(IdentifierEvent.NavigateToVerifyOtp(state.identifier))
                }
                .onFailure { throwable ->
                    state = state.copy(error = throwable.message ?: "Failed to send OTP")
                }
            state = state.copy(isLoading = false)
        }
    }
}
