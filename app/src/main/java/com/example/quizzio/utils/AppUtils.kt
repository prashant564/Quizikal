package com.example.quizzio.utils

import android.graphics.drawable.Drawable
import com.example.quizzio.R
import com.example.quizzio.views.ui.CategoryItemType

object AppUtils {

    fun getDrawableIdFromCategoryType(featureType:CategoryItemType):Int{
        return when(featureType){
            CategoryItemType.CATEGORY_ENTERTAINMENT->{
                R.drawable.entertainment
            }
            CategoryItemType.CATEGORY_MUSIC->{
                R.drawable.music
            }
            else->{
                R.drawable.entertainment
            }

        }
    }
}