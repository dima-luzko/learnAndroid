package com.example.giphy.data.repository

import com.example.giphy.data.entities.Data
import kotlinx.coroutines.flow.Flow

interface GifRepository {

    suspend fun getMemGif(): Data

    suspend fun getGif(searchName: String): Data
}