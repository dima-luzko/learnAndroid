package com.example.giphy.retrofit.common

import com.example.giphy.retrofit.RetrofitClient
import com.example.giphy.retrofit.service.APIGif

object Common {
    private const val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: APIGif
        get() = RetrofitClient.getClient(BASE_URL).create(APIGif::class.java)
}