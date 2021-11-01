package com.haxos.foodity.retrofit.services

import com.haxos.foodity.data.model.Profile
import retrofit2.Response
import retrofit2.http.*

interface IProfileService {

    @GET("/profiles")
    suspend fun getAll(@Header("Authorization") authorization: String): Response<List<Profile>>

    @GET("/profiles")
    suspend fun getById(
        @Query("id") id: Long,
        @Header("Authorization") authorization: String
    ): Response<Profile>

    @GET("/profiles")
    suspend fun getByUsername(
        @Query("username") username: String,
        @Header("Authorization") authorization: String
    ): Response<Profile>

    @PUT("/profiles")
    suspend fun saveOrUpdate(@Body profile: Profile): Response<Profile>

    @PUT("/profiles/{id}/ban")
    suspend fun toggleBan(@Path("id") id: Long) : Response<Boolean>


    @GET("/profiles/{id}/followers")
    suspend fun getFollowers(@Path("id") profileId: Long): Response<List<Long>>

    @GET("/profiles/{id}/following")
    suspend fun getFollowing(@Path("id") profileId: Long): Response<List<Long>>

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