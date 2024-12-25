package com.vunkpunk.app.domain.use_case.logOutUser

import android.content.SharedPreferences
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.patch.PatchUserDto
import com.vunkpunk.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LogOutUserUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(
    ): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            sharedPreferences.edit().remove("auth_token").apply()
            sharedPreferences.edit().remove("user_id").apply()
            emit(Resource.Success(""))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Can't Log Out"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}