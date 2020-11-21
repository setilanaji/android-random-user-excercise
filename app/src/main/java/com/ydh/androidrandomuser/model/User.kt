package com.ydh.androidrandomuser.model

sealed class User{
    data class Category(val category: String):User()
    data class Data(val user: UserModel): User()
}