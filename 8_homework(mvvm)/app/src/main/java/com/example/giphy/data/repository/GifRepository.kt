package com.example.giphy.data.repository

import com.example.giphy.data.entities.Gif

interface GifRepository {

    suspend fun getMemGif(
        apiKey: String,
        defaultName: String,
        limit: Int,
        rating: String,
        language: String
    ): List<Gif>

    suspend fun getGif(
        apiKey: String,
        searchName: String,
        limit: Int,
        rating: String,
        language: String
    ): List<Gif>
}