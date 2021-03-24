package com.example.iprofile.data

import androidx.annotation.DrawableRes

data class Comments(
     @DrawableRes val photo: Int,
     val name : String,
     val comments : String
)
