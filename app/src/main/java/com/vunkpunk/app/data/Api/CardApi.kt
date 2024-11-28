package com.vunkpunk.app.data.Api

import com.vunkpunk.app.data.dto.CardDto

interface CardApi {
    suspend fun getCards(): List<CardDto>
    suspend fun getCardById(cardId: String): CardDto
    suspend fun getCardsByUserId(userId: String): List<CardDto>
    suspend fun getCardsBySearch(paramSearch: String): List<CardDto>
}