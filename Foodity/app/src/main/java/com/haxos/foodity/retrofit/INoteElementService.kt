package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.NoteElement
import retrofit2.Response
import retrofit2.http.GET

interface INoteElementService {
    @GET("/elements")
    suspend fun getAll(): Response<List<NoteElement>>
}