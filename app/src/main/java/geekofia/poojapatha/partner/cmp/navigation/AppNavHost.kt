package geekofia.poojapatha.partner.cmp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Any = IdentifierRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        // ── Auth ──────────────────────────────────────────────────────────────
        composable<IdentifierRoute> {
            PlaceholderScreen("Identifier")
        }
        composable<VerifyOtpRoute> {
            PlaceholderScreen("Verify OTP")
        }

        // ── Onboarding ────────────────────────────────────────────────────────
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

        // ── Location Setup ────────────────────────────────────────────────────
        composable<LocationSetupRoute> {
            PlaceholderScreen("Location Setup")
        }
        composable<LocationSearchRoute> {
            PlaceholderScreen("Location Search")
        }
        composable<ExistingLocationRoute> {
            PlaceholderScreen("Existing Location")
        }

        // ── Main App ──────────────────────────────────────────────────────────
        composable<MainRoute> {
            PlaceholderScreen("Main (Bottom Nav)")
        }

        // ── Booking Detail ────────────────────────────────────────────────────
        composable<BookingDetailRoute> {
            PlaceholderScreen("Booking Detail")
        }
        composable<BookingActionOtpRoute> {
            PlaceholderScreen("Booking Action OTP")
        }

        // ── Payout Detail ─────────────────────────────────────────────────────
        composable<PayoutDetailRoute> {
            PlaceholderScreen("Payout Detail")
        }

        // ── Account Sub-screens ───────────────────────────────────────────────
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

        // ── Incoming Offer ────────────────────────────────────────────────────
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
