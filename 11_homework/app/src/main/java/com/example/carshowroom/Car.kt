package com.example.carshowroom

import androidx.annotation.DrawableRes

data class Car(
    @DrawableRes val carImage : Int,
    @DrawableRes val carIconBrand: Int,
    val carBrand: String
)
