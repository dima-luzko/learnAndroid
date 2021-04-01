package com.example.giphy.data

import com.google.gson.annotations.SerializedName

data class Images (
    @SerializedName("fixed_height_small_still")
    val original : Gif?
)