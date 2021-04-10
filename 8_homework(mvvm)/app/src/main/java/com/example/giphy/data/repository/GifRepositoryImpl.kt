package com.example.giphy.data.repository

import com.example.giphy.data.entities.Gif
import com.example.giphy.data.remote.RemoteDataSource

class GifRepositoryImpl(
    private val dataSource: RemoteDataSource
) : GifRepository {
    override suspend fun getMemGif(): List<Gif> {
        return dataSource.instance.getMemGifList().data?.map { it.images?.original } as List<Gif>
    }

    override suspend fun getGif(searchName: String): List<Gif> {
        return dataSource.instance.getGifList(searchName = searchName).data?.map { it.images?.original } as List<Gif>
    }
}