package com.example.todolist.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("category_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Task(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "completed") val completed: Boolean = false,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "path_image") val pathImage: Int?,
    @ColumnInfo(name = "category_id") val categoryId : Int,
    @ColumnInfo(name = "background_color") val backgroundColor: String?
)
