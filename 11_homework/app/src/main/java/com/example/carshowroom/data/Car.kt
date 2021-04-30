package com.example.carshowroom.data

import androidx.annotation.DrawableRes

data class Car(
    @DrawableRes val carImage : Int,
    @DrawableRes val carIconBrand: Int,
    val carBrand: String
)
