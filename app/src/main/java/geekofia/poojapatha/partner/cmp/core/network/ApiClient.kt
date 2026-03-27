package geekofia.poojapatha.partner.cmp.core.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import geekofia.poojapatha.partner.cmp.BuildConfig
import geekofia.poojapatha.partner.cmp.core.network.interceptor.AuthInterceptor
import geekofia.poojapatha.partner.cmp.core.network.interceptor.TokenExpiredInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Creates the single [Retrofit] instance used across the app.
 *
 * Snake_case ↔ camelCase conversion is handled automatically by Gson's
 * [FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES] — matching the RN app's
 * behaviour (ky + camelCase/snake_case auto-conversion).
 */
object ApiClient {

    fun build(
        authInterceptor: AuthInterceptor,
        tokenExpiredInterceptor: TokenExpiredInterceptor,
    ): Retrofit {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(tokenExpiredInterceptor)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL + "/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
