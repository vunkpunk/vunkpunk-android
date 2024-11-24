package com.vunkpunk.app.di

import com.google.gson.Gson
import com.vunkpunk.app.common.Constants
import com.vunkpunk.app.data.remote.UserApi
import com.vunkpunk.app.data.remote.UserApiImpl
import com.vunkpunk.app.data.repository.UserRepositoryImpl
import com.vunkpunk.app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserApi(client: HttpClient, gson: Gson): UserApi {
        return UserApiImpl(client, gson)
    }

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(CIO)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi): UserRepository {
        return UserRepositoryImpl(api)
    }
}