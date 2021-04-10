package com.example.giphy.data.repository

import com.example.giphy.data.entities.Data
import com.example.giphy.data.remote.RemoteDataSource

class GifRepositoryImpl(
    private val dataSource: RemoteDataSource
) : GifRepository {
    override suspend fun getMemGif(): Data {
        TODO("Not yet implemented")
    }

    override suspend fun getGif(searchName: String): Data {
        return dataSource.instance.getGifList(searchName = searchName)
    }
}