package com.haxos.foodity.retrofit.services

import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NoteRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface INotesService {

    @GET("/notes")
    suspend fun getNotesByCategory(@Query("categoryId") notesCategoryId: Long): Response<List<Note>>

    @GET("/notes")
    suspend fun getNoteById(@Query("id") id: Long): Response<Note>

    @GET("/notes")
    fun getNotesByProfile(@Query("profileId") profileId: Long): Call<List<Note>>

    @POST("/notes")
    suspend fun add(@Body request: NoteRequest): Response<Note>

    @PUT("/notes")
    suspend fun edit(@Body note: NoteRequest) : Response<Note>

    @DELETE("/notes")
    suspend fun delete(@Query("id") id: Long) : Response<Boolean>

    @POST("/notes/{id}/duplicate")
    suspend fun duplicate(@Path("id") id: Long): Response<Note>
}