package com.example.giphy.retrofit.service

import com.example.giphy.data.Gif
import retrofit2.Call
import retrofit2.http.GET

interface APIGif {

    @GET("marvel")
    fun getGifList() : Call<List<Gif>>
}