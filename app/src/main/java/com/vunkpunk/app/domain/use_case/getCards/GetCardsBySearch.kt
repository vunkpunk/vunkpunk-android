package com.vunkpunk.app.domain.use_case.getCards

import android.util.Log
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.toCardMini
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.repository.CardRepository
import com.vunkpunk.app.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCardsBySearch @Inject constructor(
    private val cardRepository: CardRepository, private val photoRepository: PhotoRepository
) {
    operator fun invoke(searchText: String): Flow<Resource<List<CardMini>>> = flow {
        try {
            emit(Resource.Loading<List<CardMini>>())
            val cardsDto = cardRepository.getCardsBySearch(searchText)
            val cardsMini =
                cardsDto.map { it.toCardMini(photoRepository.getCardPhotoById(it.uploaded_images[0].id.toString())) }
            Log.d("GetCardsMiniFromUserUseCase", cardsMini.toString())
            emit(Resource.Success<List<CardMini>>(cardsMini))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<CardMini>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<CardMini>>("Couldn't reach server. Check your internet connection."))
        }
    }
}