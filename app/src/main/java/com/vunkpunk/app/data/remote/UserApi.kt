package com.vunkpunk.app.data.remote

import com.vunkpunk.app.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    suspend fun getUserById(userId: String): UserDto
}