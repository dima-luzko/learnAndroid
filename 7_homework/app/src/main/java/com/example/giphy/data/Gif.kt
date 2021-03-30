package com.example.giphy.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Gif (

  @Expose
  @SerializedName("imageurl")
  val gif: String? = null
)