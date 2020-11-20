package com.ydh.androidrandomuser.model

import com.google.gson.annotations.SerializedName

data class IdModel(
        @SerializedName("name")
        val name: String,
        @SerializedName("value")
        val value: String
)