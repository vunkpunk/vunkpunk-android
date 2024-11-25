package com.vunkpunk.app.domain.use_case.getCards

import android.util.Log
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.toCardMini
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetUserCardsMiniUseCase @Inject constructor(
    private val repository: CardRepository
) {
    private fun getUserCards(cards: List<CardMini>, userId: String): List<CardMini>{
        val output = mutableListOf<CardMini>()
        cards.map { if(it.user.toString() == userId) output.add(it)}
        return output
    }

    operator fun invoke(userId: String): Flow<Resource<List<CardMini>>> = flow {
        try {
            emit(Resource.Loading<List<CardMini>>())
            val cardsDto = repository.getCards()
            Log.d("GetCardsMiniUseCase", cardsDto.toString())
            val cardsMini = cardsDto.map { it.toCardMini() }
            val userCardsMini = getUserCards(cardsMini, userId)
            Log.d("GetUserCardsMiniUseCase", userCardsMini.toString())
            emit(Resource.Success<List<CardMini>>(userCardsMini))
        } catch (e: HttpException) {
            emit(Resource.Error<List<CardMini>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<CardMini>>("Couldn't reach server. Check your internet connection."))
        }

    }
}