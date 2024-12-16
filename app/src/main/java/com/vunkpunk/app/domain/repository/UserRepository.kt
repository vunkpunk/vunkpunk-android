package com.vunkpunk.app.domain.repository

import com.vunkpunk.app.data.dto.UserDto

interface UserRepository {
    suspend fun getUserById(userId: String): UserDto
}