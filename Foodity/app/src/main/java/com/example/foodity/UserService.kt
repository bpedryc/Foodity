package com.example.foodity

import com.example.foodity.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("/users")
    fun getUsers() : Call<List<User>>

    @GET("/users")
    fun getUsersByEmail(@Query("email") email: String): Call<User>

    @GET("/users")
    fun getUsersByUsername(@Query("username") username: String): Call<User>
}