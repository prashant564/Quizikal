package com.prashD.quizzio.repository

import com.prashD.quizzio.database.TriviaDatabase
import com.prashD.quizzio.network.RetrofitClientInstance
import com.prashD.quizzio.views.ui.TriviaUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class TriviaRepository(private val db: TriviaDatabase) {

    fun getAllTrivia(category: String, page: Int, limit: Int): Flow<Response<MutableList<TriviaUI>>> = flow {
        val triviaList = RetrofitClientInstance.api.getTriviaFromNetwork(category, page, limit)
        emit(triviaList)
    }.flowOn(Dispatchers.IO)

    suspend fun insertTrivia(triviaUI: TriviaUI) = db.TriviaDao().insertTrivia(triviaUI)

    suspend fun deleteTrivia(triviaUI: TriviaUI) = db.TriviaDao().deleteTrivia(triviaUI)

    fun getAllFavTrivia() = db.TriviaDao().getAllTrivia()
}