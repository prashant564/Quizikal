package com.example.quizzio

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp

class BaseApp: Application() {
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=this
        FirebaseApp.initializeApp(this)
    }
}