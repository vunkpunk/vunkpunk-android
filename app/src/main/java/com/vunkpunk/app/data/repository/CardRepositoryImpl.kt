package com.vunkpunk.app.data.repository

import android.util.Log
import com.vunkpunk.app.data.Api.CardApi
import com.vunkpunk.app.data.dto.CardDto
import com.vunkpunk.app.data.dto.UserDto
import com.vunkpunk.app.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val api: CardApi
) : CardRepository {

    override suspend fun getCards(): List<CardDto> {
        return api.getCards()
    }

    override suspend fun getCardById(cardId: String): CardDto {
        return api.getCardById(cardId)
    }

}