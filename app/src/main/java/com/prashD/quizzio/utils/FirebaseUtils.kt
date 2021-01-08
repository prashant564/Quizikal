package com.prashD.quizzio.utils

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.prashD.quizzio.BaseApp

object FirebaseUtils {
    var mFirebaseAnalytics = FirebaseAnalytics.getInstance(BaseApp.context)

    enum class Event {
        FEATURE_CLICK,
        CLICK
    }

    enum class PARAM {
        FEATURE_TYPE,
        ACTION_NAME
    }

    enum class ACTION {
        FAVOURITES, SIGN_OUT, SUBMIT_BTN, HINT_BTN, REVEAL_ANS_BTN, FAB_ADD_TO_FAVOURITES, SIGN_IN,
//        ENTERTAINMENT, MUSIC, GENERAL, SPORTSANDLEISURE, HISTORYANDHOLIDAYS, FOODANDRINKS,
//        TOYSANDGAMES, SCIENCEANDNATURE,PEOPLEANDPLACES, LANGUAGE, KIDS, RELIGIONANDMYTHOLOGY,
//        ARTANDLITERATURE, TECHANDVIDEOGAMES, MATHEMATICS
    }

    fun sendClickEvents(event: Event, bundle: Bundle) {
        mFirebaseAnalytics.logEvent(event.name, bundle)
    }

    fun sendClickEvent(action: ACTION, event: Event = Event.CLICK) {
        val bundle = Bundle()
        bundle.putString(PARAM.ACTION_NAME.name, action.name)
//        sendClickEvents(event,bundle)
        mFirebaseAnalytics.logEvent(action.name, bundle)
    }

}