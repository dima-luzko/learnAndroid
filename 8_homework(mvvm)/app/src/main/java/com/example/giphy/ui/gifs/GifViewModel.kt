package com.example.giphy.ui.gifs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphy.data.entities.Gif
import com.example.giphy.data.repository.GifRepository
import kotlinx.coroutines.launch

class GifViewModel constructor(
    private val gifRepository: GifRepository
) : ViewModel() {

    private val _gif = MutableLiveData<List<Gif>>()
    val gif: LiveData<List<Gif>> = _gif

    fun getGifList(
        apiKey: String,
        query: String,
        limit: Int,
        rating: String,
        language: String
    ) {
        viewModelScope.launch {
            val data = gifRepository.getGif(
                apiKey = apiKey,
                searchName = query,
                limit = limit,
                rating = rating,
                language = language
            )
            _gif.postValue(data)
        }
    }

    fun getMemGifList(
        apiKey: String,
        defaultName: String,
        limit: Int,
        rating: String,
        language: String
    ) {
        viewModelScope.launch {
            val data = gifRepository.getMemGif(
                apiKey = apiKey,
                defaultName = defaultName,
                limit = limit,
                rating = rating,
                language = language
            )
            _gif.value = data
        }
    }
}