package com.vunkpunk.app.domain.use_case.logInUser

import android.util.Log
import com.vunkpunk.app.common.ErrorConstants.LOGIN_ERROR
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.common.Token.TOKEN
import com.vunkpunk.app.data.dto.post.LogInUserDto
import com.vunkpunk.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LogInUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(logInUser: LogInUserDto): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading<String>())
            val token = repository.logInUser(logInUser)
            if (token == LOGIN_ERROR){
                emit(Resource.Error(token))
            } else {
                TOKEN.value = token
                Log.d("TOKEN", TOKEN.value)
                emit(Resource.Success<String>(token))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}