package com.example.foodity

import com.example.foodity.data.model.User
import retrofit2.Call
import retrofit2.http.GET

interface UserAPI {
    @GET("/users/all")
    fun getUsers() : Call<List<User>>


    /*@GET("/3/movie/popular")
    fun getMovies(@Query("api_key") key: String): Call<PopularMovies>*/
}