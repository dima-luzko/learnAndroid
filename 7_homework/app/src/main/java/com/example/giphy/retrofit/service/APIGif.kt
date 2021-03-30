package com.example.giphy.retrofit.service

import com.example.giphy.retrofit.data.Gif
import retrofit2.Call
import retrofit2.http.GET

interface APIGif {

    @GET
    fun getGifList() : Call<List<Gif>>
}