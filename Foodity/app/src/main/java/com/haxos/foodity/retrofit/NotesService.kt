package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.Note
import retrofit2.Call
import retrofit2.http.GET

interface NotesService {

    @GET("/notes")
    fun getAll(): Call<List<Note>>
}