package com.example.giphy.data.repository

import com.example.giphy.data.entities.Gif

interface GifRepository {

    suspend fun getMemGif(): List<Gif>

    suspend fun getGif(searchName: String): List<Gif>
}