package geekofia.poojapatha.partner.cmp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import geekofia.poojapatha.partner.cmp.core.network.ApiClient
import geekofia.poojapatha.partner.cmp.core.network.interceptor.AuthInterceptor
import geekofia.poojapatha.partner.cmp.core.network.interceptor.TokenExpiredInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        authInterceptor: AuthInterceptor,
        tokenExpiredInterceptor: TokenExpiredInterceptor,
    ): Retrofit = ApiClient.build(
        authInterceptor = authInterceptor,
        tokenExpiredInterceptor = tokenExpiredInterceptor,
    )
}
