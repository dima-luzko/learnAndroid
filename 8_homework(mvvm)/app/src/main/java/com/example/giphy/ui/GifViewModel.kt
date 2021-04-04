package com.example.giphy.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.giphy.data.repository.GifRepository

class GifViewModel @ViewModelInject constructor(
    private val gifRepository: GifRepository
): ViewModel() {

}