package com.ydh.androidrandomuser.model

import com.google.gson.annotations.SerializedName

data class LocationModel(
        @SerializedName("street")
        val street: StreetModel,
        @SerializedName("city")
        val city: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("postcode")
        val postcode: Any
)