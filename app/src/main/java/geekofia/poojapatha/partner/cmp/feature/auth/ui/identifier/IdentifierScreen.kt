package geekofia.poojapatha.partner.cmp.feature.auth.ui.identifier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import geekofia.poojapatha.partner.cmp.ui.theme.Spacing

@Composable
fun IdentifierScreen(
    onNavigateToVerifyOtp: (String) -> Unit,
    viewModel: IdentifierViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is IdentifierEvent.NavigateToVerifyOtp -> {
                    keyboard?.hide()
                    onNavigateToVerifyOtp(event.identifier)
                }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = Spacing.lg.dp)
                .imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // ── Branding ──────────────────────────────────────────────────────
            Text(
                text = "🪔",
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.height(Spacing.lg.dp))
            Text(
                text = "PoojaPatha Partner",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(Spacing.sm.dp))
            Text(
                text = "Sign in to manage your bookings",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(Spacing.huge.dp))

            // ── Input ─────────────────────────────────────────────────────────
            OutlinedTextField(
                value = state.identifier,
                onValueChange = viewModel::onIdentifierChange,
                label = { Text("Mobile number or email") },
                placeholder = { Text("e.g. 9876543210") },
                isError = state.error != null,
                supportingText = state.error?.let { { Text(it) } },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = { viewModel.sendOtp() },
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(Spacing.xl.dp))

            // ── CTA ───────────────────────────────────────────────────────────
            Button(
                onClick = viewModel::sendOtp,
                enabled = state.canSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                } else {
                    Text("Continue")
                }
            }
        }
    }
}
