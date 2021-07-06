package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.KeycloakUser
import com.haxos.foodity.data.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IUserService {
    @GET("/users")
    fun getUsers() : Call<List<User>>

    @GET("/users/{username}/roles")
    suspend fun getUserRoles(@Query("username") username: String): Response<List<String>>

    @GET("/users")
    fun getUsersByEmail(@Query("email") email: String): Call<User>

    @GET("/users")
    fun getUsersByUsername(@Query("username") username: String): Call<User>

    @POST("/users")
    fun createUser(@Body user: KeycloakUser): Call<KeycloakUser>
}