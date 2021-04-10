package com.example.giphy.data.entities

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val data: List<GifEntity>?
)


