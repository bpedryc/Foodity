package com.haxos.foodity.retrofit.services

import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.data.model.NotesCategoryRequest
import retrofit2.Response
import retrofit2.http.*

interface INotesCategoriesService {

    @GET("/categories")
    suspend fun getCategoriesByProfileId(@Query("profileId") profileId: Long) : Response<List<NotesCategory>>

    @POST("/categories")
    suspend fun createCategory(@Body categoryRequest: NotesCategoryRequest) : Response<NotesCategory>

    @PUT("/categories")
    suspend fun edit(@Body request: NotesCategoryRequest): Response<NotesCategory>

    @DELETE("/categories")
    suspend fun delete(@Query("id") id: Long) : Response<Boolean>
}