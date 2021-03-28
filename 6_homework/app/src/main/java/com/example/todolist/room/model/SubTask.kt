package com.example.todolist.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Task::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("task_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SubTask(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "task_id") val taskId: Int
)
