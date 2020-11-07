package com.example.quizzio.network

import com.example.quizzio.views.ui.TriviaUI
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaAPI {
        @GET("trivia")
    suspend fun getTriviaFromNetwork(@Query("category") type: String, @Query("page") page: Int=1, @Query("limit") limit: Int):
            Response<MutableList<TriviaUI>>

    
}