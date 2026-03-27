package geekofia.poojapatha.partner.cmp.feature.auth.data.repository

import geekofia.poojapatha.partner.cmp.core.data.local.AppPreferences
import geekofia.poojapatha.partner.cmp.feature.auth.data.remote.AuthApi
import geekofia.poojapatha.partner.cmp.feature.auth.data.remote.dto.SendOtpRequest
import geekofia.poojapatha.partner.cmp.feature.auth.data.remote.dto.VerifyOtpRequest
import geekofia.poojapatha.partner.cmp.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val preferences: AppPreferences,
) : AuthRepository {

    override suspend fun sendOtp(identifier: String): Result<Unit> = runCatching {
        authApi.sendOtp(SendOtpRequest(identifier = identifier, via = "whatsapp"))
    }

    override suspend fun verifyOtp(identifier: String, otp: String): Result<Unit> = runCatching {
        val response = authApi.verifyOtp(VerifyOtpRequest(identifier = identifier, code = otp, via = "whatsapp"))
        preferences.setAuthToken(response.accessToken)
    }
}
