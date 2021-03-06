package com.example.todolist.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "path_image") val pathImage: Int?,
    @ColumnInfo(name = "background_color") val backgroundColor: String?
)
