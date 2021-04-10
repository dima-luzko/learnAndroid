package com.example.giphy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphy.data.entities.Data

import com.example.giphy.data.repository.GifRepository

class GifViewModel constructor(
    private val gifRepository: GifRepository
): ViewModel() {


    private val _gif = MutableLiveData<Data>()
    private var gif: LiveData<Data> = _gif

    private suspend fun getAllViewModelGif(gifList: Data){
        gifRepository.getGif(gifList.data?.map { it.images?.original?.gifUrl })
        //gifList.data?.map { it.images }
    }


}