package com.example.todolist.data

import androidx.annotation.DrawableRes

data class Tasks(
    @DrawableRes val icon: Int,
    val taskName: String
)
