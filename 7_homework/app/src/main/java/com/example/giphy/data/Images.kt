package com.example.giphy.data

import com.google.gson.annotations.SerializedName

data class Images (
    @SerializedName("original")
    val original : List<Gif>
)