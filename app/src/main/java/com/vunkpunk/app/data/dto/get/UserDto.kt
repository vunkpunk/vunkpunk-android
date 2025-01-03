package com.vunkpunk.app.data.dto.get

import android.graphics.Bitmap
import com.vunkpunk.app.domain.model.User

data class UserDto(
    val contact: String,
    val description: String,
    val first_name: String,
    val last_name: String,
    val username: String,
    val photo: String?,
    val faculty: String,
    val dormitory: String
)


fun UserDto.toUser(photo: Bitmap): User {
    return User(
        contact = contact,
        description = description,
        first_name = first_name,
        last_name = last_name,
        username = username,
        photo = photo,
        faculty = faculty,
        dormitory = dormitory
    )
}
