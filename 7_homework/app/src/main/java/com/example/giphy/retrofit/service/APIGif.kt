package com.example.giphy.retrofit.service

import com.example.giphy.data.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIGif {

    @GET("search?api_key=J4P5R8kdKobkpJa3X6cIv2bVHkxVMmdQ&limit=25&offset=0&rating=g&lang=en")
    fun getGifList(@Query("q") searchName: String) : Call<Data>

    @GET("search?api_key=J4P5R8kdKobkpJa3X6cIv2bVHkxVMmdQ&q=mems&limit=25&offset=0&rating=g&lang=en")
    fun getMemGifList() : Call<Data>
}