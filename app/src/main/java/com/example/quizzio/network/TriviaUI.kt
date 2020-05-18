package com.example.quizzio.network

import android.os.Parcelable
import com.squareup.moshi.Json

data class TriviaUI(
    @Json(name = "_id")
    val id:String,
    val seenStatus:Boolean,
    val category:String,
    val question:String,
    val answer:String,
    @Json(name = "itemid")
    val itemId: String
//    val id: String,
//    // used to map img_src from the JSON to imgSrcUrl in our class
//    @Json(name = "img_src") val imgSrcUrl: String,
//    val type: String,
//    val price: Double
    )