package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.Profile
import retrofit2.Call
import retrofit2.http.GET

interface ProfileService {

    @GET("/profiles")
    fun getAll(): Call<List<Profile>>
}