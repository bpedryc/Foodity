package com.haxos.foodity.retrofit

import com.haxos.foodity.data.elementRequest.ImageNoteElementRequest
import com.haxos.foodity.data.elementRequest.ListNoteElementRequest
import com.haxos.foodity.data.elementRequest.NoteElementsRequest
import com.haxos.foodity.data.elementRequest.TextNoteElementRequest
import com.haxos.foodity.data.model.ImageNoteElement
import com.haxos.foodity.data.model.ListNoteElement
import com.haxos.foodity.data.model.NoteElement
import com.haxos.foodity.data.model.TextNoteElement
import retrofit2.Response
import retrofit2.http.*

interface INoteElementService {
    @GET("/elements")
    suspend fun getAll(): Response<List<NoteElement>>

    @GET("/elements")
    suspend fun getByNoteId(@Query("noteId") noteId: Long): Response<List<NoteElement>>

    @PUT("/elements")
    suspend fun edit(@Body request: NoteElementsRequest): Response<List<NoteElement>>

    @PUT("/text-elements")
    suspend fun edit(@Body request: TextNoteElementRequest): Response<TextNoteElement>

    @POST("/text-elements")
    suspend fun create(@Body request: TextNoteElementRequest): Response<TextNoteElement>

    @PUT("/list-elements")
    suspend fun edit(@Body request: ListNoteElementRequest): Response<ListNoteElement>

    @POST("/list-elements")
    suspend fun create(@Body request: ListNoteElementRequest): Response<ListNoteElement>

    @PUT("/image-elements")
    suspend fun edit(@Body request: ImageNoteElementRequest): Response<ImageNoteElement>

    @POST("/image-elements")
    suspend fun create(@Body request: ImageNoteElementRequest): Response<ImageNoteElement>
}