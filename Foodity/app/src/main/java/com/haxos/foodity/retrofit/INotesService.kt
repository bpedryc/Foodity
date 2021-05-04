package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NotesCategory
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface INotesService {

    @GET("/categories")
    fun getAllCategories(): Call<List<NotesCategory>>

    @GET("/categories")
    fun getCategoriesByUsername(@Query("username") username: String): Response<List<NotesCategory>>

    @GET("/notes")
    fun getNotesByCategory(@Query("categoryId") notesCategoryId: Long): Call<List<Note>>

    @GET("/notes")
    fun getNoteById(@Query("id") id: Long): Call<Note>

    @GET("/notes")
    fun getNotesByProfile(@Query("profileId") profileId: Long): Call<List<Note>>
}