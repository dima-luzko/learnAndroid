package com.example.giphy.data.repository

import com.example.giphy.data.entities.Gif
import com.example.giphy.data.remote.RemoteDataSource
import com.example.giphy.utils.Constants

class GifRepositoryImpl(
    private val dataSource: RemoteDataSource
) : GifRepository {

    override suspend fun getMemGif(
        apiKey: String,
        defaultName: String,
        limit: Int,
        rating: String,
        language: String
    ): List<Gif> {
        return dataSource.instance.getMemGifList(
            apiKey = apiKey,
            defaultName = defaultName,
            limit = limit,
            rating = rating,
            language = language
        ).data?.map { it.images?.original } as List<Gif>
    }

    override suspend fun getGif(
        apiKey: String,
        searchName: String,
        limit: Int,
        rating: String,
        language: String
    ): List<Gif> {
        return dataSource.instance.getGifList(
            apiKey = apiKey,
            searchName = searchName,
            limit = limit,
            rating = rating,
            language = language
        ).data?.map { it.images?.original } as List<Gif>
    }
}