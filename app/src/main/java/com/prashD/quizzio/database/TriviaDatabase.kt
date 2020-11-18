package com.prashD.quizzio.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prashD.quizzio.views.ui.TriviaUI

@Database(entities = [TriviaUI::class], version = 1)
abstract class TriviaDatabase: RoomDatabase() {

    abstract fun TriviaDao() : TriviaDao

    companion object {

        @Volatile
        private var instance : TriviaDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TriviaDatabase::class.java,
            "trivia_db.db"
        ).build()
    }

}