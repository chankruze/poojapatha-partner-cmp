package geekofia.poojapatha.partner.cmp.feature.auth.data.remote.dto

data class SendOtpRequest(
    val identifier: String,
    // Gson LOWER_CASE_WITH_UNDERSCORES converts portalType → portal_type
    val portal: String = "partner",
    val via: String = "whatsapp"
)
