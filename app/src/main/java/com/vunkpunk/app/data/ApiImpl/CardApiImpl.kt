package com.vunkpunk.app.data.ApiImpl

import android.util.Log
import com.google.gson.Gson
import com.vunkpunk.app.common.Constants.BASE_URL
import com.vunkpunk.app.common.Token.TOKEN
import com.vunkpunk.app.data.Api.CardApi
import com.vunkpunk.app.data.Api.UserApi
import com.vunkpunk.app.data.dto.CardDto
import com.vunkpunk.app.data.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class CardApiImpl @Inject constructor(
    private val client: HttpClient,
    private val gson: Gson
) : CardApi {

    override suspend fun getCards(): List<CardDto> {
        val resp = client.get("$BASE_URL/sales") {
            header("Authorization", "Token ${TOKEN}")
        }
        val cards = gson.fromJson(resp.bodyAsText(), Array<CardDto>::class.java).asList()
        return cards
    }

    override suspend fun getCardById(cardId: String): CardDto {
        val resp = client.get("$BASE_URL/sales/$cardId") {
            header("Authorization", "Token ${TOKEN}")
        }
        val card = gson.fromJson(resp.bodyAsText(), CardDto::class.java)
        Log.d("CardApiImpl", card.toString())
        return card
    }

}