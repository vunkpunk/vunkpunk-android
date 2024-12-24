package com.vunkpunk.app.di

import com.google.gson.Gson
import com.vunkpunk.app.common.Token.TOKEN
import com.vunkpunk.app.data.Api.CardApi
import com.vunkpunk.app.data.Api.PhotoApi
import com.vunkpunk.app.data.Api.LoginUserApi
import com.vunkpunk.app.data.Api.UserApi
import com.vunkpunk.app.data.ApiImpl.CardApiImpl
import com.vunkpunk.app.data.ApiImpl.PhotoApiImpl
import com.vunkpunk.app.data.ApiImpl.LoginUserApiImpl
import com.vunkpunk.app.data.ApiImpl.UserApiImpl
import com.vunkpunk.app.data.repository.CardRepositoryImpl
import com.vunkpunk.app.data.repository.PhotoRepositoryImpl
import com.vunkpunk.app.data.repository.LoginUserRepositoryImpl
import com.vunkpunk.app.data.repository.UserRepositoryImpl
import com.vunkpunk.app.domain.repository.CardRepository
import com.vunkpunk.app.domain.repository.PhotoRepository
import com.vunkpunk.app.domain.repository.LoginUserRepository
import com.vunkpunk.app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideTOKEN(): String {
        return TOKEN.value
    }
    @Provides
    @Singleton
    fun provideUserApi(client: HttpClient, gson: Gson, token: String): UserApi {
        return UserApiImpl(client, gson, token)
    }

    @Provides
    @Singleton
    fun provideLoginUserApi(client: HttpClient, gson: Gson): LoginUserApi {
        return LoginUserApiImpl(client, gson)
    }

    @Provides
    @Singleton
    fun provideCardApi(client: HttpClient, gson: Gson, token: String): CardApi {
        return CardApiImpl(client, gson, token)
    }

    @Provides
    @Singleton
    fun providePhotoApi(client: HttpClient, gson: Gson, token: String): PhotoApi {
        return PhotoApiImpl(client, gson, token)
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

    @Provides
    @Singleton
    fun provideLoginUserRepository(api: LoginUserApi): LoginUserRepository {
        return LoginUserRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideCardRepository(api: CardApi): CardRepository {
        return CardRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(api: PhotoApi): PhotoRepository {
        return PhotoRepositoryImpl(api)
    }
}