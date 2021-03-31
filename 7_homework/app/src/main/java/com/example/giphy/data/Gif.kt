package com.example.giphy.data

import com.google.gson.annotations.SerializedName

data class Gif(
    @SerializedName("embed_url")
    val gif: String? = null,

    @SerializedName("images")
    val images: String? = null
)