package com.haxos.foodity.retrofit

import com.haxos.foodity.data.model.Token
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface IAuthService {

    @POST("token")
    @FormUrlEncoded
    fun getToken(
            @Field("username") username: String,
            @Field("password") password: String
    ): Call<Token>
}