package com.example.todolist.room.dao

import androidx.room.*
import com.example.todolist.room.model.Category

@Dao
interface SubTaskDao {
    @Query("SELECT * FROM subTask")
    fun getAll(): List<Category>

    @Query("SELECT * FROM subTask WHERE uid = :uid LIMIT 1")
    fun findById(uid: Int): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg subTask: Category)
}