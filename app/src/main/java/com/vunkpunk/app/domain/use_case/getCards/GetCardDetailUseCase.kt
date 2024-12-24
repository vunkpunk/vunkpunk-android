package com.vunkpunk.app.domain.use_case.getCards

import android.util.Log
import android.util.Printer
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.get.toCardDetail
import com.vunkpunk.app.domain.model.CardDetail
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.repository.CardRepository
import com.vunkpunk.app.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCardDetailUseCase @Inject constructor(
    private val cardRepository: CardRepository,
    private val photoRepository: PhotoRepository
) {
    operator fun invoke(cardId: String): Flow<Resource<CardDetail>> = flow {
        try {
            emit(Resource.Loading<CardDetail>())

            val cardDto = cardRepository.getCardById(cardId)
            val cardPhoto = cardDto.uploaded_images.map {it -> photoRepository.getCardPhotoById(it.id.toString())}
            val cardDetail = cardDto.toCardDetail(cardPhoto)

            emit(Resource.Success<CardDetail>(cardDetail))
        } catch (e: HttpException) {
            emit(
                Resource.Error<CardDetail>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<CardDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}