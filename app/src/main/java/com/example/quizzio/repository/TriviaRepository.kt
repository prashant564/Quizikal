package com.example.quizzio.repository

import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.network.RetrofitClientInstance
import com.example.quizzio.views.ui.TriviaUI

class TriviaRepository(private val db: TriviaDatabase) {

    suspend fun getAllTrivia(category: String, page:Int, limit:Int) = RetrofitClientInstance.api.getTriviaFromNetwork(category,page,limit)

    suspend fun insertTrivia(triviaUI: TriviaUI) = db.TriviaDao().insertTrivia(triviaUI)

    suspend fun deleteTrivia(triviaUI: TriviaUI) = db.TriviaDao().deleteTrivia(triviaUI)

    fun getAllFavTrivia() = db.TriviaDao().getAllTrivia()
}