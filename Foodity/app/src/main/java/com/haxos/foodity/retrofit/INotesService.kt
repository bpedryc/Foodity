package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NoteRequest
import com.haxos.foodity.data.model.NotesCategory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface INotesService {

    @GET("/notes")
    fun getNotesByCategory(@Query("categoryId") notesCategoryId: Long): Call<List<Note>>

    @GET("/notes")
    fun getNoteById(@Query("id") id: Long): Call<Note>

    @GET("/notes")
    fun getNotesByProfile(@Query("profileId") profileId: Long): Call<List<Note>>

    @POST("/notes")
    suspend fun add(@Body request: NoteRequest): Response<Note>

    @DELETE("/notes")
    suspend fun delete(@Query("id") noteId: Long) : Response<ResponseBody>
}