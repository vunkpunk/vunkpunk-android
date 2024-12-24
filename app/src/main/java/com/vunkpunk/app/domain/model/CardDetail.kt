package com.vunkpunk.app.domain.model

import android.graphics.Bitmap

data class CardDetail(
    val description: String,
    val dormitory: String,
    val id: Int,
    val is_published: Boolean,
    val photos: List<Bitmap>,
    val price: String,
    val rating: Double,
    val title: String,
    val user: Int,
    val contact: String
)