package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.KeycloakUser
import com.haxos.foodity.data.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface IUserService {
    @GET("/users")
    suspend fun getUsers() : Response<List<User>>

    @GET("/users/{username}/roles")
    suspend fun getUserRoles(@Path("username") username: String): Response<List<String>>

    @GET("/users")
    fun getUsersByEmail(@Query("email") email: String): Call<User>

    @GET("/users")
    fun getUsersByUsername(@Query("username") username: String): Call<User>

    @POST("/users")
    fun createUser(@Body user: KeycloakUser): Call<KeycloakUser>
}