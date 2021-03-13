package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.NotesCategory
import retrofit2.Call
import retrofit2.http.GET

interface NotesService {

    @GET("/categories")
    fun getAllCategories(): Call<List<NotesCategory>>
}