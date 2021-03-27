package com.example.todolist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
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
                .build()

        val PREPOPULATE_DATA = listOf(
            Category(
                uid = 1,
                name = "Category",
                pathImage = "src/main/res/drawable/icon_design.png"
            ),
            Category(
                uid = 2,
                name = "Learning",
                pathImage = "src/main/res/drawable/icon_book.png"
            ),
            Category(
                uid = 3,
                name = "Homework",
                pathImage = "src/main/res/drawable/icon_homework.png"
            ),
            Category(
                uid = 4,
                name = "Traveling",
                pathImage = "src/main/res/drawable/icon_traveling.png"
            ),
            Category(
                uid = 5,
                name = "Workout",
                pathImage = "src/main/res/drawable/icon_workout.png"
            )
        )
    }

}