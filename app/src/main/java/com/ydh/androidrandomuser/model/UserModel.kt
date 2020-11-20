package com.ydh.androidrandomuser.model

import com.google.gson.annotations.SerializedName

data class UserModel(
        @SerializedName("id")
        val id: IdModel,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("name")
        val name: NameModel,
        @SerializedName("location")
        val location: LocationModel,
        @SerializedName("email")
        val email: String,
        @SerializedName("dob")
        val dob: DobModel,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("call")
        val cell: String,
        @SerializedName("nat")
        val nat: String,
        @SerializedName("picture")
        val picture: PictureModel
)