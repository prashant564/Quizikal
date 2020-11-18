package com.prashD.quizzio.utils

import com.prashD.quizzio.R
import com.prashD.quizzio.views.ui.CategoryItemType

object AppUtils {

    fun getColorIdFromCategoryType(category: String):Int{
        return when(category){
            "Entertainment"-> R.color.entertainment
            "Tech and Video Games"-> R.color.tech_and_video_games
            "Art and Literature"-> R.color.art_and_literature
            "General"-> R.color.general
            "History and Holidays"-> R.color.history_and_holidays
            "Kids"-> R.color.kids
            "Language"-> R.color.language
            "Mathematics"-> R.color.mathematics
            "Music"-> R.color.music
            "People and Places"-> R.color.people_and_places
            "Science and Nature"-> R.color.science_and_nature
            "Sports and Leisure"-> R.color.sports_and_leisure
            "Toys and Games"-> R.color.toys_and_games
            "Religion and Mythology"-> R.color.religion_and_mythology
            else->R.color.tech_and_video_games
        }
    }


    fun getDrawableIdFromCategoryType(featureType:CategoryItemType):Int{
        return when(featureType){
            CategoryItemType.CATEGORY_ENTERTAINMENT->{
                R.drawable.entertainment
            }
            CategoryItemType.CATEGORY_MUSIC->{
                R.drawable.music
            }
            CategoryItemType.CATEGORY_FOOD_AND_DRINK->{
                R.drawable.food_and_drink
            }
            CategoryItemType.CATEGORY_GENERAL->{
                R.drawable.general
            }
            CategoryItemType.CATEGORY_HISTORY_AND_HOLIDAYS->{
                R.drawable.historyandholidays
            }
            CategoryItemType.CATEGORY_KIDS->{
                R.drawable.kids
            }
            CategoryItemType.CATEGORY_LANGUAGE->{
                R.drawable.language
            }
            CategoryItemType.CATEGORY_MATHEMATICS->{
                R.drawable.mathematics
            }
            CategoryItemType.CATEGORY_PEOPLE_AND_PLACES->{
                R.drawable.people_and_places
            }
            CategoryItemType.CATEGORY_RELIGION_AND_MYTHOLOGY->{
                R.drawable.religion_and_mythology
            }
            CategoryItemType.CATEGORY_SCIENCE_AND_NATURE->{
                R.drawable.science_and_nature
            }
            CategoryItemType.CATEGORY_SPORTS_AND_LEISURE->{
                R.drawable.sportandleisure
            }
            CategoryItemType.CATEGORY_TECH_AND_VIDEO_GAMES->{
                R.drawable.tech_and_video_games
            }
            CategoryItemType.CATEGORY_TOYS_AND_GAMES->{
                R.drawable.toys_and_games
            }
            CategoryItemType.CATEGORY_ART_AND_LITERATURE->{
                R.drawable.art_and_literature
            }
            else->{
                R.drawable.entertainment
            }

        }
    }

    fun createHint(hint: CharArray): String{
        var hintString=""
        for( i in hint.indices step 2) {
            if(hint[i]==' '){
                hintString+=' '
            }else{
                hintString+=hint[i]
            }
            if(i+1<hint.size){
                if(hint[i+1]!=' ') {
                    hintString+='_'
                }else{
                    hintString+=' '
                }
            }
        }
        return hintString
    }
}