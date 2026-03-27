package geekofia.poojapatha.partner.cmp.navigation

import kotlinx.serialization.Serializable

// ── Auth ─────────────────────────────────────────────────────────────────────
@Serializable
object IdentifierRoute

@Serializable
data class VerifyOtpRoute(val identifier: String)

// ── Onboarding ────────────────────────────────────────────────────────────────
@Serializable
object OnboardingRoleSelectionRoute

@Serializable
object OnboardingDetailsRoute

@Serializable
object OnboardingKycRoute

@Serializable
object OnboardingContactRoute

@Serializable
object OnboardingBankRoute

@Serializable
object OnboardingAddressRoute

@Serializable
object OnboardingStatusRoute

// ── Location Setup ────────────────────────────────────────────────────────────
@Serializable
object LocationSetupRoute

@Serializable
object LocationSearchRoute

@Serializable
object ExistingLocationRoute

// ── Main App (tab shell) ──────────────────────────────────────────────────────
@Serializable
object MainRoute

// ── Home Tab ──────────────────────────────────────────────────────────────────
@Serializable
object HomeRoute

// ── Bookings Tab ──────────────────────────────────────────────────────────────
@Serializable
object BookingsRoute

@Serializable
data class BookingDetailRoute(val uuid: String)

@Serializable
data class BookingActionOtpRoute(val bookingUuid: String, val action: String)

// ── Earnings Tab ──────────────────────────────────────────────────────────────
@Serializable
object EarningsRoute

@Serializable
data class PayoutDetailRoute(val uuid: String)

// ── Referrals Tab ─────────────────────────────────────────────────────────────
@Serializable
object ReferralsRoute

// ── Account Tab ───────────────────────────────────────────────────────────────
@Serializable
object AccountRoute

@Serializable
object EditProfileRoute

@Serializable
object ManageAddressesRoute

@Serializable
object SettingsRoute

@Serializable
object LanguageRoute

@Serializable
object HelpSupportRoute

@Serializable
object AboutRoute

// ── Incoming Offer (full-screen modal) ───────────────────────────────────────
@Serializable
data class IncomingOfferRoute(val uuid: String)
