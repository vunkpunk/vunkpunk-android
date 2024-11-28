package com.vunkpunk.app.domain.use_case.getCards

import android.util.Log
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.toCardMini
import com.vunkpunk.app.data.dto.toUser
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.model.User
import com.vunkpunk.app.domain.repository.CardRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCardsMiniUseCase @Inject constructor(
    private val repository: CardRepository
) {
    operator fun invoke(): Flow<Resource<List<CardMini>>> = flow {
        try {
            emit(Resource.Loading<List<CardMini>>())
            val cardsDto = repository.getCards()
            Log.d("GetCardsMiniUseCase", cardsDto.toString())
            val cardsMini = cardsDto.map { it.toCardMini() }
            Log.d("GetCardsMiniUseCase", cardsMini.toString())
            emit(Resource.Success<List<CardMini>>(cardsMini))
        } catch (e: HttpException) {
            emit(Resource.Error<List<CardMini>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<CardMini>>("Couldn't reach server. Check your internet connection."))
        }
    }
}