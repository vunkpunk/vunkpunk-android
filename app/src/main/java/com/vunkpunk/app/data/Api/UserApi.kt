package com.vunkpunk.app.data.Api

import com.vunkpunk.app.data.dto.get.UserDto

interface UserApi {
    suspend fun getUserById(userId: String): UserDto
}