package geekofia.poojapatha.partner.cmp.feature.auth.domain.repository

interface AuthRepository {
    suspend fun sendOtp(identifier: String): Result<Unit>
    suspend fun verifyOtp(identifier: String, otp: String): Result<Unit>
}
