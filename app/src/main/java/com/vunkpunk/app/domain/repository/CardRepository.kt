package com.vunkpunk.app.domain.repository

import com.vunkpunk.app.data.dto.CardDto

interface CardRepository {
    suspend fun getCards(): List<CardDto>
}