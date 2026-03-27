package geekofia.poojapatha.partner.cmp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import geekofia.poojapatha.partner.cmp.feature.auth.ui.identifier.IdentifierScreen
import geekofia.poojapatha.partner.cmp.feature.auth.ui.verifyotp.VerifyOtpScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val rootViewModel: RootViewModel = hiltViewModel()
    val authState by rootViewModel.authState.collectAsStateWithLifecycle()

    // ── Loading splash — wait for DataStore to emit the initial token state ──
    if (authState is RootViewModel.AuthState.Loading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val startDestination: Any = when (authState) {
        is RootViewModel.AuthState.Authenticated -> MainRoute
        else -> IdentifierRoute
    }

    // ── Handle 401 forced-logout from anywhere in the app ────────────────────
    LaunchedEffect(Unit) {
        rootViewModel.tokenExpiredEvents.collect {
            navController.navigate(IdentifierRoute) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        // ── Auth ──────────────────────────────────────────────────────────────
        composable<IdentifierRoute> {
            IdentifierScreen(
                onNavigateToVerifyOtp = { identifier ->
                    navController.navigate(VerifyOtpRoute(identifier))
                },
            )
        }
        composable<VerifyOtpRoute> {
            VerifyOtpScreen(
                onNavigateToMain = {
                    navController.navigate(MainRoute) {
                        popUpTo(IdentifierRoute) { inclusive = true }
                    }
                },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        // ── Onboarding (Phase 4) ──────────────────────────────────────────────
        composable<OnboardingRoleSelectionRoute> {
            PlaceholderScreen("Onboarding: Role Selection")
        }
        composable<OnboardingDetailsRoute> {
            PlaceholderScreen("Onboarding: Details")
        }
        composable<OnboardingKycRoute> {
            PlaceholderScreen("Onboarding: KYC")
        }
        composable<OnboardingContactRoute> {
            PlaceholderScreen("Onboarding: Contact")
        }
        composable<OnboardingBankRoute> {
            PlaceholderScreen("Onboarding: Bank")
        }
        composable<OnboardingAddressRoute> {
            PlaceholderScreen("Onboarding: Address")
        }
        composable<OnboardingStatusRoute> {
            PlaceholderScreen("Onboarding: Status")
        }

        // ── Location Setup (Phase 4) ──────────────────────────────────────────
        composable<LocationSetupRoute> {
            PlaceholderScreen("Location Setup")
        }
        composable<LocationSearchRoute> {
            PlaceholderScreen("Location Search")
        }
        composable<ExistingLocationRoute> {
            PlaceholderScreen("Existing Location")
        }

        // ── Main App (Phase 5+) ───────────────────────────────────────────────
        composable<MainRoute> {
            PlaceholderScreen("Main App")
        }

        // ── Booking Detail (Phase 6) ──────────────────────────────────────────
        composable<BookingDetailRoute> {
            PlaceholderScreen("Booking Detail")
        }
        composable<BookingActionOtpRoute> {
            PlaceholderScreen("Booking Action OTP")
        }

        // ── Payout Detail (Phase 7) ───────────────────────────────────────────
        composable<PayoutDetailRoute> {
            PlaceholderScreen("Payout Detail")
        }

        // ── Account Sub-screens (Phase 9) ─────────────────────────────────────
        composable<EditProfileRoute> {
            PlaceholderScreen("Edit Profile")
        }
        composable<ManageAddressesRoute> {
            PlaceholderScreen("Manage Addresses")
        }
        composable<SettingsRoute> {
            PlaceholderScreen("Settings")
        }
        composable<LanguageRoute> {
            PlaceholderScreen("Language")
        }
        composable<HelpSupportRoute> {
            PlaceholderScreen("Help & Support")
        }
        composable<AboutRoute> {
            PlaceholderScreen("About")
        }

        // ── Incoming Offer (Phase 12) ─────────────────────────────────────────
        composable<IncomingOfferRoute> {
            PlaceholderScreen("Incoming Offer")
        }
    }
}

@Composable
private fun PlaceholderScreen(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = name)
    }
}
