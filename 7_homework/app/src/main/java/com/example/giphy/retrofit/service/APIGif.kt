package com.example.giphy.retrofit.service

import com.example.giphy.data.Data
import retrofit2.Call
import retrofit2.http.GET

interface APIGif {

    @GET("search?api_key=J4P5R8kdKobkpJa3X6cIv2bVHkxVMmdQ&q=dogs&limit=5&offset=0&rating=g&lang=en")
    fun getGifList() : Call<Data>
}