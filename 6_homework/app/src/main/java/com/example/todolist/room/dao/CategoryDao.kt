package com.example.todolist.room.dao

import androidx.room.*
import com.example.todolist.room.model.Category
import com.example.todolist.room.model.Task

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE uid = :uid LIMIT 1")
    fun findById(uid: Int): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: List<Category>)

}