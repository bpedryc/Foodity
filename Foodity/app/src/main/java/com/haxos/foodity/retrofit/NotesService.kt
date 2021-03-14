package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.NotesCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NotesService {

    @GET("/categories")
    fun getAllCategories(): Call<List<NotesCategory>>

    @GET("/categories")
    fun getCategoriesByUsername(@Query("username") username: String): Call<List<NotesCategory>>
}