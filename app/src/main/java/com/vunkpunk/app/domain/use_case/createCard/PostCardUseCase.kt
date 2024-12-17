package com.vunkpunk.app.domain.use_case.createCard

import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.PostCardDto
import com.vunkpunk.app.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostCardUseCase @Inject constructor(
    private val repository: CardRepository
){
    operator fun invoke(title: String, price: String, description: String): Flow<Resource<PostCardDto>> = flow{
        try {
            emit(Resource.Loading<PostCardDto>())
            val postCardDto = PostCardDto(title, price, description, true)
            repository.postCard(postCardDto)
            emit(Resource.Success<PostCardDto>(postCardDto))
        } catch (e: HttpException) {
            emit(
                Resource.Error<PostCardDto>(
                    e.localizedMessage ?: "Can't post card"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<PostCardDto>("Couldn't reach server. Check your internet connection."))
        }
    }
}