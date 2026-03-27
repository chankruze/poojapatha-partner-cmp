package geekofia.poojapatha.partner.cmp.feature.auth.data.remote.dto

// Server returns { "access_token": "..." }
// Gson LOWER_CASE_WITH_UNDERSCORES maps access_token → accessToken
data class VerifyOtpResponse(
    val accessToken: String,
)
