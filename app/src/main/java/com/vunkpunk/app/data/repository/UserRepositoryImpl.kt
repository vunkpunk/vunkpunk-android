package com.vunkpunk.app.data.repository

import android.util.Log
import com.vunkpunk.app.data.remote.UserApi
import com.vunkpunk.app.data.remote.UserApiImpl
import com.vunkpunk.app.data.remote.dto.UserDto
import com.vunkpunk.app.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override suspend fun getUserById(userId: String): UserDto {
        Log.d("UserRepositoryImpl", "getUserById with id=$userId was called")
        return api.getUserById(userId)
    }

}