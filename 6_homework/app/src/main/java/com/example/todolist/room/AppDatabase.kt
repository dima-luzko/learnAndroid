package com.example.todolist.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.room.dao.CategoryDao
import com.example.todolist.room.dao.SubTaskDao
import com.example.todolist.room.dao.TaskDao
import com.example.todolist.room.model.Category
import com.example.todolist.room.model.SubTask
import com.example.todolist.room.model.Task

@Database(entities = [Category::class, Task::class, SubTask::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun taskDao(): TaskDao
    abstract fun subTaskDao(): SubTaskDao
}