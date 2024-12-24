package com.vunkpunk.app.data.ApiImpl

import com.google.gson.Gson
import com.vunkpunk.app.common.Constants.BASE_URL
import com.vunkpunk.app.data.Api.PhotoApi
import com.vunkpunk.app.data.dto.get.CardDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsBytes
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class PhotoApiImpl @Inject constructor(
    private val client: HttpClient,
    private val gson: Gson,
    private val token: String
) : PhotoApi {

    override suspend fun getCardPhotoById(photoId: String): ByteArray {
        val resp = client.get("$BASE_URL/image/salecard/$photoId") {
            header("Authorization", "Token $token")
        }
        return resp.bodyAsBytes()
    }

    override suspend fun getUserPhotoById(userId: String): ByteArray {
        val resp = client.get("$BASE_URL/image/user/$userId") {
            header("Authorization", "Token $token")
        }
        return resp.bodyAsBytes()
    }
}