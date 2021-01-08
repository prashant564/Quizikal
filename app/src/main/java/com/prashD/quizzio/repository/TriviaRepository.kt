package com.prashD.quizzio.repository

import com.prashD.quizzio.database.TriviaDatabase
import com.prashD.quizzio.network.RetrofitClientInstance
import com.prashD.quizzio.views.ui.TriviaUI

class TriviaRepository(private val db: TriviaDatabase) {

    suspend fun getAllTrivia(category: String, page: Int, limit: Int) =
        RetrofitClientInstance.api.getTriviaFromNetwork(category, page, limit)

    suspend fun insertTrivia(triviaUI: TriviaUI) = db.TriviaDao().insertTrivia(triviaUI)

    suspend fun deleteTrivia(triviaUI: TriviaUI) = db.TriviaDao().deleteTrivia(triviaUI)

    fun getAllFavTrivia() = db.TriviaDao().getAllTrivia()
}