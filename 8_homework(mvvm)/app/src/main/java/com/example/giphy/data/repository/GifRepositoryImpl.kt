package com.example.giphy.data.repository

import com.example.giphy.data.entities.Data
import com.example.giphy.data.remote.GifApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GifRepositoryImpl @Inject constructor(private val gifApi: GifApi): GifRepository {

    override fun getMemGif(): Flow<Data> {
        return flow {
            val gifMem = gifApi.getMemGifList()
            emit(gifMem)
        }.flowOn(Dispatchers.IO)
    }

    override fun getGif(searchName: String): Flow<Data> {
        return flow {
            val gif = gifApi.getGifList(searchName)
            emit(gif)
        }.flowOn(Dispatchers.IO)

    }

}