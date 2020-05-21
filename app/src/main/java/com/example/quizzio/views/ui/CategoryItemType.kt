package com.example.quizzio.views.ui

import android.os.Parcelable
import com.example.quizzio.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class CategoryItemType constructor(var type :String, var color: Int):Parcelable {
    CATEGORY_ENTERTAINMENT( "entertainment", R.color.entertainment),
    CATEGORY_MUSIC("music",R.color.music),

}
