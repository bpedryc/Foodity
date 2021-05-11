package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.data.model.NotesCategoryRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface INotesCategoriesService {

    @GET("/categories")
    fun getAllCategories(): Call<List<NotesCategory>>

    @GET("/categories")
    suspend fun getCategoriesByUsername(@Query("username") username: String) : Response<List<NotesCategory>>

    @POST("/categories")
    suspend fun createCategory(@Body categoryRequest: NotesCategoryRequest) : Response<NotesCategory>
}