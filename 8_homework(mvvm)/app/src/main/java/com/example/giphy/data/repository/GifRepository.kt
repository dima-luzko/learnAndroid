package com.example.giphy.data.repository

import com.example.giphy.data.entities.Data
import kotlinx.coroutines.flow.Flow

interface GifRepository {

    fun getMemGif (): Flow<Data>

    fun getGif(searchName: String): Flow<Data>
}