package com.example.todolist.room.dao

import androidx.room.*
import com.example.todolist.room.model.Category
import com.example.todolist.room.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE uid = :uid LIMIT 1")
    fun findById(uid: Int): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(uid: Int)
}