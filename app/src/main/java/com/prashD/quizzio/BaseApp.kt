package com.prashD.quizzio

import android.app.Application
import android.content.Context
import com.prashD.quizzio.utils.ResourceUtils
import com.prashD.quizzio.utils.SharedPrefUtils
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp

class BaseApp: Application() {
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=this
        FirebaseApp.initializeApp(this)
        MobileAds.initialize(this, ResourceUtils.toString(R.string.admob_app_id))
        SharedPrefUtils.increaseLaunchCount()
    }
}