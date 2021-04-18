package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.Profile
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface IProfileService {

    @GET("/profiles")
    fun getAll(): Call<List<Profile>>

    @GET("/profiles")
    suspend fun getById(@Query("id") id: Long): Response<Profile>

    @GET("/profiles")
    suspend fun getByUsername(@Query("username") username: String): Response<Profile>

    @POST("/profiles")
    suspend fun post(@Body profile: Profile): Response<Profile>

    @GET("/profiles/follower")
    suspend fun getFollower(
        @Query("id") profileId: Long,
        @Query("followerId") followerId: Long
    ): Response<Profile>


    @GET("/profiles/{id}/followers")
    suspend fun getFollowers(@Path("id") profileId: Long): Response<List<Profile>>

    @GET("/profiles/{id}/following")
    suspend fun getFollowing(@Path("id") profileId: Long): Response<List<Profile>>

    @PUT("/profiles/{id}/followers")
    suspend fun addFollower(
        @Path("id") profileId: Long,
        @Query("followerId") followerId: Long
    )

    @DELETE("/profiles/{id}/followers")
    suspend fun removeFollower(
        @Path("id") profileId: Long,
        @Query("followerId") followerId: Long
    )
}