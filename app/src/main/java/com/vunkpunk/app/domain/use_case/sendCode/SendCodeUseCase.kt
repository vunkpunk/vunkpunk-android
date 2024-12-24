package com.vunkpunk.app.domain.use_case.sendCode

import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.post.SendCodeDto
import com.vunkpunk.app.domain.repository.LoginUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SendCodeUseCase @Inject constructor(
    private val repository: LoginUserRepository,
) {
    operator fun invoke(userCode: SendCodeDto): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val isAuth = repository.sendCode(userCode)
            if (isAuth){
                emit(Resource.Success<Boolean>(isAuth))
            } else {
                emit(Resource.Error("Incorrect code"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}