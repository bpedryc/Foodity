package com.haxos.foodity.retrofit.services

import com.haxos.foodity.data.model.ProfileLog
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IProfileLogService {

    @GET("/profilelogs")
    suspend fun getFollowingLogs(@Query("profileId") profileId: Long): Response<List<ProfileLog>>
}
