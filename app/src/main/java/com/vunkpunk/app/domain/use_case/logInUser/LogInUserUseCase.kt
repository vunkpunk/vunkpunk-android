package com.vunkpunk.app.domain.use_case.logInUser

import android.util.Log
import com.vunkpunk.app.common.ErrorConstants.LOGIN_ERROR
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.common.Token.ID
import com.vunkpunk.app.common.Token.TOKEN
import com.vunkpunk.app.data.dto.post.LogInUserDto
import com.vunkpunk.app.data.dto.response.LogInUserDtoResponseDto
import com.vunkpunk.app.domain.repository.LoginUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LogInUserUseCase @Inject constructor(
    private val repository: LoginUserRepository,
) {
    operator fun invoke(logInUser: LogInUserDto): Flow<Resource<LogInUserDtoResponseDto>> = flow {
        try {
            emit(Resource.Loading<LogInUserDtoResponseDto>())
            val response = repository.logInUser(logInUser)
            val token = response.auth_token
            val id = response.id
            val error = response.non_field_errors
            if (error[0] != ""){
                emit(Resource.Error(error[0]))
            } else {
                TOKEN.value = token
                ID.value = id.toString()
                Log.d("TOKEN", TOKEN.value)
                Log.d("ID", ID.value)
                emit(Resource.Success<LogInUserDtoResponseDto>(response))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}