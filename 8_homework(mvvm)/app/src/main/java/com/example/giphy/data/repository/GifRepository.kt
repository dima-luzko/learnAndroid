package com.example.giphy.data.repository

import com.example.giphy.data.entities.Data
import com.example.giphy.data.remote.GifApi
import retrofit2.Response
import javax.inject.Inject

class GifRepository @Inject constructor(private val gifApi: GifApi) {

    suspend fun fetchMemGif() : Result<Data> {
        return
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }
}