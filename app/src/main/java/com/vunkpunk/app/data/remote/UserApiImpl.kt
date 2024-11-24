package com.vunkpunk.app.data.remote

import android.util.Log
import com.google.gson.Gson
import com.vunkpunk.app.common.Constants.BASE_URL
import com.vunkpunk.app.common.Token.TOKEN
import com.vunkpunk.app.data.remote.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class UserApiImpl @Inject constructor(
    private val client: HttpClient,
    private val gson: Gson
) : UserApi {

    override suspend fun getUserById(userId: String): UserDto {
        val resp = client.get("$BASE_URL/user/$userId") {
            header("Authorization", "Token ${TOKEN}")
        }
        val user = gson.fromJson(resp.bodyAsText(), UserDto::class.java)
        Log.d("UserApiImpl", user.toString())
        return user
    }

}