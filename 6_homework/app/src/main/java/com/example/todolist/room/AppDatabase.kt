package com.example.todolist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todolist.R
import com.example.todolist.adapter.TasksAdapter
import com.example.todolist.ioThread
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

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "todo_list.db"
            )
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        ioThread {
                            getInstance(context).categoryDao().insert(PREPOPULATE_DATA)
                        }
                    }
                })
                .allowMainThreadQueries()
                .build()

        val PREPOPULATE_DATA = listOf(
            Category(
                name = "Design",
                pathImage = R.drawable.icon_design,
                backgroundColor = "#FF018786"
            ),
            Category(
                name = "Learning",
                pathImage = R.drawable.icon_book,
                backgroundColor = "#DC143C"
            ),
            Category(
                name = "Homework",
                pathImage = R.drawable.icon_homework,
                backgroundColor = "#FB7500"
            ),
            Category(
                name = "Traveling",
                pathImage = R.drawable.icon_traveling,
                backgroundColor = "#4B0082"
            ),
            Category(
                name = "Workout",
                pathImage = R.drawable.icon_workout,
                backgroundColor = "#008000"
            )
        )
    }

}