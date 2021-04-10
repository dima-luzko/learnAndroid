package com.example.giphy.data.remote

import com.example.giphy.data.entities.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {

    @GET("search")
    suspend fun getGifList(
        @Query("api_key") apiKey: String,
        @Query("q") searchName: String,
        @Query("limit") limit: Int,
        @Query("rating") rating: String,
        @Query("lang") language: String
    ): Data

    @GET("search")
    suspend fun getMemGifList(
        @Query("api_key") apiKey: String,
        @Query("q") defaultName: String,
        @Query("limit") limit: Int,
        @Query("rating") rating: String,
        @Query("lang") language: String
    ): Data
}