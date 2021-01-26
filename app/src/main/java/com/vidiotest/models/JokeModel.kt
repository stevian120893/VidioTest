package com.vidiotest.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
class JokeModel(

    @SerializedName("created_at")
    var createdAt: String?,

    @SerializedName("icon_url")
    var iconUrl: String?,

    @SerializedName("id:")
    var id: String?,

    @SerializedName("updated_at")
    var updatedAt: String?,

    @SerializedName("url")
    var url: String?,

    @SerializedName("value")
    var value: String?

): Parcelable