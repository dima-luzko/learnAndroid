package com.example.todolist.data

import androidx.annotation.DrawableRes

data class Category(
    val color: Int,
    @DrawableRes val icon: Int,
    val name: String,
    val totalTask: Int
)
