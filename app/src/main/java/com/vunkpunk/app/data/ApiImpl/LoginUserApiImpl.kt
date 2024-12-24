package com.vunkpunk.app.data.ApiImpl

import android.util.Log
import com.google.gson.Gson
import com.vunkpunk.app.common.Constants.BASE_URL
import com.vunkpunk.app.common.ErrorConstants.LOGIN_ERROR
import com.vunkpunk.app.data.Api.LoginUserApi
import com.vunkpunk.app.data.dto.post.LogInUserDto
import com.vunkpunk.app.data.dto.post.SendCodeDto
import com.vunkpunk.app.data.dto.post.SignUpUserDto
import com.vunkpunk.app.data.dto.response.LogInUserDtoResponseDto
import com.vunkpunk.app.data.dto.response.SignUpUserResponseFailDto
import com.vunkpunk.app.data.dto.response.LogInUserDtoResponseFailDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class LoginUserApiImpl @Inject constructor(
    private val client: HttpClient,
    private val gson: Gson
) : LoginUserApi {
    override suspend fun signUpUser(signUpUser: SignUpUserDto): String {
        val userJson = gson.toJson(signUpUser)
        val responseJson = client.post("$BASE_URL/auth/users/") {
            contentType(ContentType.Application.Json)
            setBody(userJson)
        }
        if (responseJson.status.value == 400) {
            val response =
                gson.fromJson(responseJson.bodyAsText(), SignUpUserResponseFailDto::class.java)
            if (!response.username.isNullOrEmpty()) {
                return response.username[0]
            }
            if (!response.email.isNullOrEmpty()) {
                return response.email[0]
            }
        }
        if (responseJson.status.value == 401) {
            return "Unauthorized"
        }
        if (responseJson.status.value != 201) {
            return responseJson.bodyAsText()
        }
        return ""
    }

    override suspend fun logInUser(logInUser: LogInUserDto): LogInUserDtoResponseDto {
        val codeJson = gson.toJson(logInUser)
        val responseJson = client.post("$BASE_URL/auth/token/login/") {
            contentType(ContentType.Application.Json)
            setBody(codeJson)
        }
        val response =
            gson.fromJson(responseJson.bodyAsText(), LogInUserDtoResponseDto::class.java)
                ?: LogInUserDtoResponseDto()
        Log.d("RESPONSE", response.toString())
        return response
    }

    override suspend fun sendCode(userCode: SendCodeDto): Boolean {
        val codeJson = gson.toJson(userCode)
        val responseJson = client.post("$BASE_URL/auth/activate/") {
            contentType(ContentType.Application.Json)
            setBody(codeJson)
        }
        return responseJson.status.value == 201 || responseJson.status.value == 200
    }

}