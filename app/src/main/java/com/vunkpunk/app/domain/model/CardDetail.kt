package com.vunkpunk.app.domain.model

data class CardDetail(
    val description: String,
    val id: Int,
    val is_published: Boolean,
    val price: String,
    val rating: Double,
    val title: String,
    val user: Int
)