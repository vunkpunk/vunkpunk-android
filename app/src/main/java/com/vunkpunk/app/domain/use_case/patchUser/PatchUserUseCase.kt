package com.vunkpunk.app.domain.use_case.patchUser

import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.patch.PatchUserDto
import com.vunkpunk.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PatchUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(
        first_name: String,
        second_name: String,
        dormitory: String,
        faculty: String,
        contact: String,
        description: String,
        image: ByteArray
    ): Flow<Resource<PatchUserDto>> = flow {
        try {
            emit(Resource.Loading<PatchUserDto>())
            val patchUserDto =
                PatchUserDto(first_name, second_name, dormitory, faculty, contact, description)
            userRepository.updateUserProfile(patchUserDto, image)
            emit(Resource.Success<PatchUserDto>(patchUserDto))
        } catch (e: HttpException) {
            emit(
                Resource.Error<PatchUserDto>(
                    e.localizedMessage ?: "Can't post card"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<PatchUserDto>("Couldn't reach server. Check your internet connection."))
        }
    }
}