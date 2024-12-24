package com.vunkpunk.app.domain.use_case.signUpUser

import android.util.Log
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.post.SignUpUserDto
import com.vunkpunk.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(signUpUser: SignUpUserDto): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading<String>())
            val error = repository.signUpUser(signUpUser)
            if (error == ""){
                emit(Resource.Success<String>(error))
            } else {
                emit(Resource.Error(error))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}