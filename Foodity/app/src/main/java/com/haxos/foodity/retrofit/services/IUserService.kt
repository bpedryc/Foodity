package com.haxos.foodity.retrofit.services

import com.haxos.foodity.data.model.KeycloakUser
import com.haxos.foodity.data.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserService {
    @GET("/users")
    suspend fun getUsers() : Response<List<User>>

    @GET("/users/{username}/roles")
    suspend fun getUserRoles(@Path("username") username: String): Response<List<String>>

    @POST("/users")
    fun createUser(@Body user: KeycloakUser): Call<KeycloakUser>
}