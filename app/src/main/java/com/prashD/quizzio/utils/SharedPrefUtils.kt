package com.prashD.quizzio.utils

import android.content.Context
import android.content.SharedPreferences
import com.prashD.quizzio.BaseApp

object SharedPrefUtils {
    private val sPreferences = BaseApp.context.getSharedPreferences(
        AppConstants.PrefUtils.quizikal, Context.MODE_PRIVATE
    )
    private val mPreferenceEditor: SharedPreferences.Editor

    init {
        mPreferenceEditor = sPreferences.edit()
    }

    fun putData(key: String, value: Any) {
        when (value) {
            is String ->
                mPreferenceEditor.putString(key, value)
            is Int ->
                mPreferenceEditor.putInt(key, value)
            is Boolean ->
                mPreferenceEditor.putBoolean(key, value)

        }
        mPreferenceEditor.apply()
    }

    fun getLaunchCount(): Int {
        return sPreferences.getInt(AppConstants.launchCount, 0)
    }

    fun increaseLaunchCount() {
        val count = getLaunchCount()
        putData(AppConstants.launchCount, count + 1)
    }

    fun resetLaunchCount() {
        putData(AppConstants.launchCount, AppConstants.REPEAT_LAUNCH_COUNT)
    }
}