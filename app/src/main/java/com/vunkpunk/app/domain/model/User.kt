package com.vunkpunk.app.domain.model

import android.graphics.Bitmap

data class User(
    val contact: String?,
    val description: String,
    val first_name: String,
    val last_name: String,
    val username: String,
    val photo: Bitmap,
    val faculty: String,
    val dormitory: String
)