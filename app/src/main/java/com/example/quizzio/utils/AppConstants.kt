package com.example.quizzio.utils

import com.example.quizzio.views.fragments.EntertainmentFragment
import com.example.quizzio.views.fragments.HomeFragment

object AppConstants {

    const val BASE_URL = "https://quizikal.herokuapp.com/api/v1/"

    var categoryType = "categoryType"
    var categoryTag = "categoryTag"

    object CategoryTag{
        var Entertainment = "Entertainment"
        var Music = "Music"
    }
}