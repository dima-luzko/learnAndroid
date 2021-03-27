package com.example.todolist.room.dao

import androidx.room.*
import com.example.todolist.room.model.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE uid = :uid LIMIT 1")
    fun findById(uid: Int): Category

}