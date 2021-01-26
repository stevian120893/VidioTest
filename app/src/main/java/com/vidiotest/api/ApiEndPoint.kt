package com.vidiotest.api

import com.vidiotest.models.JokeModel
import com.vidiotest.models.SearchJokeModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @GET("jokes/categories")
    fun getJokeCategory(): Call<ArrayList<String>>

    @GET("jokes/random")
    fun getRandomJoke(): Call<JokeModel>

    @GET("jokes/random")
    fun getRandomJokeByCategory(
        @Query("category") category: String
    ): Call<JokeModel>

    @GET("jokes/search")
    fun getJokeByKeyword(
        @Query("query") keyword: String
    ): Call<SearchJokeModel>
}
