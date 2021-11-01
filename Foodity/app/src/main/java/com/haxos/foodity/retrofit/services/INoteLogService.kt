package com.haxos.foodity.retrofit.services

import com.haxos.foodity.data.model.NoteLog
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface INoteLogService {

    @GET("/notelogs")
    suspend fun getFollowingLogs(@Query("profileId") profileId: Long): Response<List<NoteLog>>
}
