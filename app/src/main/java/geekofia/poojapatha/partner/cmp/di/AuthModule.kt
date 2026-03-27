package geekofia.poojapatha.partner.cmp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import geekofia.poojapatha.partner.cmp.feature.auth.data.remote.AuthApi
import geekofia.poojapatha.partner.cmp.feature.auth.data.repository.AuthRepositoryImpl
import geekofia.poojapatha.partner.cmp.feature.auth.domain.repository.AuthRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}
