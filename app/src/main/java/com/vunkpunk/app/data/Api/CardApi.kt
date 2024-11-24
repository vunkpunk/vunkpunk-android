package com.vunkpunk.app.data.Api

import com.vunkpunk.app.data.dto.CardDto

interface CardApi {
    suspend fun getCards(): List<CardDto>
}