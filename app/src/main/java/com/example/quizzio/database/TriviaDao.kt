package com.example.quizzio.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quizzio.views.ui.TriviaUI

@Dao
interface TriviaDao {
    @Query("SELECT * FROM trivia")
    fun getAllTrivia() : LiveData<List<TriviaUI>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrivia(post: TriviaUI)

    @Delete
    suspend fun deleteTrivia(post: TriviaUI)
}