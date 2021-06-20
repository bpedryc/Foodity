package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.NoteElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface INoteElementService {
    @GET("/elements")
    suspend fun getAll(): Response<List<NoteElement>>

    @GET("/elements")
    suspend fun getByNoteId(@Query("noteId") noteId: Long): Response<List<NoteElement>>
}