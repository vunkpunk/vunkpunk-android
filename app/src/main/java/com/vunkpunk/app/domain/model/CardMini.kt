package com.vunkpunk.app.domain.model

import android.graphics.Bitmap

data class CardMini(
    val id: Int,
    val is_published: Boolean,
    val dormitory: String,
    val photo: Bitmap,
    val price: String,
    val rating: Double,
    val title: String,
    val user: Int
)
// общага
// картинка