package com.ydh.androidrandomuser

import com.google.gson.annotations.SerializedName
import com.ydh.androidrandomuser.model.UserModel

data class UserResponse(
    @SerializedName("results")
    val result: List<UserModel>
)