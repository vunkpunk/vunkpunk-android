package com.vunkpunk.app.data.dto

import com.vunkpunk.app.domain.model.User

data class UserDto(
    val contact: String,
    val description: String,
    val first_name: String,
    val last_name: String,
    val username: String
)


fun UserDto.toUser(): User {
    return User(
        contact = contact,
        description = description,
        first_name = first_name,
        last_name = last_name,
        username = username,
    )
}
