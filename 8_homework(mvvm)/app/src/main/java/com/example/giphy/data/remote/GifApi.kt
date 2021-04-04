package com.example.giphy.data.remote

import com.example.giphy.data.entities.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {

    @GET("search?api_key=J4P5R8kdKobkpJa3X6cIv2bVHkxVMmdQ&limit=25&offset=0&rating=g&lang=en")
    suspend fun getGifList(@Query("q") searchName: String): Data

    @GET("search?api_key=J4P5R8kdKobkpJa3X6cIv2bVHkxVMmdQ&q=mems&limit=25&offset=0&rating=g&lang=en")
    suspend fun getMemGifList(): Data
}