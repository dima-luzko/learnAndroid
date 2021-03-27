package com.example.todolist.room.dao

import androidx.room.*
import com.example.todolist.room.model.Category

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Category>

    @Query("SELECT * FROM task WHERE uid = :uid LIMIT 1")
    fun findById(uid: Int): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg task: Category)

    @Update
    fun update(task: Category)

    @Delete
    fun delete(task: Category)
}