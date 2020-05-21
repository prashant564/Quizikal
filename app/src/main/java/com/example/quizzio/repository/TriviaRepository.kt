package com.example.quizzio.repository

import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.network.RetrofitClientInstance
import com.example.quizzio.views.ui.TriviaUI

class TriviaRepository(private val db: TriviaDatabase) {

    suspend fun getAllTrivia(category: String) = RetrofitClientInstance.api.getTriviaFromNetwork(category)

    fun getUserSolvedTrivia() = db.TriviaDao().getAllTrivia()

    suspend fun insertTrivia(triviaUI: TriviaUI) = db.TriviaDao().insertTrivia(triviaUI)

}