package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.Profile
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IProfileService {

    @GET("/profiles")
    fun getAll(): Call<List<Profile>>

    @GET("/profiles")
    fun getById(@Query("id") id: Long): Call<Profile>

    @GET("/profiles")
    suspend fun getByUsername(@Query("username") username: String): Response<Profile>
}