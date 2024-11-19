package com.vunkpunk.app.domain.repository

import com.vunkpunk.app.data.remote.dto.UserDto
import dagger.Provides

interface UserRepository {
    suspend fun getUserById(userId: String): UserDto
}