package com.example.giphy.retrofit

import com.example.giphy.retrofit.service.APIGif
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val instance: APIGif = Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(APIGif::class.java)
    }


