package com.prashD.quizzio.views.ui

import android.os.Parcelable
import com.prashD.quizzio.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class CategoryItemType constructor(var type :String, var color: Int):Parcelable {
    CATEGORY_ENTERTAINMENT( "entertainment", R.color.entertainment),
    CATEGORY_MUSIC("art_and_literature",R.color.art_and_literature),
    CATEGORY_FOOD_AND_DRINK("food_and_drinks",R.color.food_and_drink),
    CATEGORY_GENERAL("general",R.color.general),
    CATEGORY_HISTORY_AND_HOLIDAYS("history_and_holidays",R.color.history_and_holidays),
    CATEGORY_KIDS("kids",R.color.kids),
    CATEGORY_LANGUAGE("language",R.color.language),
    CATEGORY_MATHEMATICS("mathematics",R.color.mathematics),
    CATEGORY_PEOPLE_AND_PLACES("people_and_places",R.color.people_and_places),
    CATEGORY_RELIGION_AND_MYTHOLOGY("religion_and_mythology",R.color.religion_and_mythology),
    CATEGORY_SCIENCE_AND_NATURE("science_and_nature",R.color.science_and_nature),
    CATEGORY_SPORTS_AND_LEISURE("sports_and_leisure",R.color.sports_and_leisure),
    CATEGORY_TECH_AND_VIDEO_GAMES("tech_and_video_games",R.color.tech_and_video_games),
    CATEGORY_TOYS_AND_GAMES("toys_and_games",R.color.toys_and_games),
    CATEGORY_ART_AND_LITERATURE("art_and_literature",R.color.art_and_literature),
}
