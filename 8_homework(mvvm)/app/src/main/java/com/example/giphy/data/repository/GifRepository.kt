package com.example.giphy.data.repository

import com.example.giphy.data.remote.GifApi
import javax.inject.Inject

class GifRepository @Inject constructor(private val gifApi: GifApi) {
}