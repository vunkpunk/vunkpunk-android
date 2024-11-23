package com.vunkpunk.domain.use_case.getUser

import android.util.Log
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.remote.dto.toUser
import com.vunkpunk.app.domain.model.User
import com.vunkpunk.app.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())
            val user = repository.getUserById(userId).toUser()
//            val user = User("1", "2", "3", "4", "5")
            Log.d("GetUserUseCase", "repository.getUserById with id=$userId was called")
            emit(Resource.Success<User>(user))
        } catch (e: HttpException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<User>("Couldn't reach server. Check your internet connection."))
        }
    }
}