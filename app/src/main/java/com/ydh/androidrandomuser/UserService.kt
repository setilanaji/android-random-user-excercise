package com.ydh.androidrandomuser

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/")
    fun getAllUser(
        @Query("results")
        result: Int = 100
    ): Call<UserResponse>
}