package com.example.giphy.ui

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

    fun getGifList(query: String) {
        viewModelScope.launch {
            val data = gifRepository.getGif(query)
            _gif.postValue(data)
        }
    }

    fun getMemGifList() {
        viewModelScope.launch {
            val data = gifRepository.getMemGif()
            _gif.value = data
        }
    }
}