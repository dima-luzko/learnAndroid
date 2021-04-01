package com.example.giphy.data

import com.google.gson.annotations.SerializedName

data class GifEntity (
    @SerializedName("images")
    val images: Images?
)
