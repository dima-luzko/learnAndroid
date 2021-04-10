package com.example.giphy.data.entities

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val data: List<GifEntity>?
)

data class Gif(
    @SerializedName("url")
    val gifUrl: String
)

data class GifEntity(
    @SerializedName("images")
    val images: Images?
)

data class Images(
    @SerializedName("preview_gif")
    val original: Gif?
)