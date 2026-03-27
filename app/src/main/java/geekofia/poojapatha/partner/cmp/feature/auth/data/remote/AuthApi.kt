package geekofia.poojapatha.partner.cmp.feature.auth.data.remote

import geekofia.poojapatha.partner.cmp.feature.auth.data.remote.dto.SendOtpRequest
import geekofia.poojapatha.partner.cmp.feature.auth.data.remote.dto.VerifyOtpRequest
import geekofia.poojapatha.partner.cmp.feature.auth.data.remote.dto.VerifyOtpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/send_otp")
    suspend fun sendOtp(@Body request: SendOtpRequest)

    @POST("auth/verify_otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): VerifyOtpResponse
}
