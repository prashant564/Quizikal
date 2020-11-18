package com.prashD.quizzio.views.ui

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "trivia")
data class TriviaUI(
    @SerializedName("_id")
    @PrimaryKey
    val id:String,
    val seenStatus:Boolean,
    val category:String,
    val question:String,
    val answer:String,
    @SerializedName("itemid")
    val itemId: String
    ): Parcelable