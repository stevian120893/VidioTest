package com.vidiotest.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
class SearchJokeModel(

    @SerializedName("total")
    var total: String?,

    @SerializedName("result")
    var listJoke: ArrayList<JokeModel>?

): Parcelable