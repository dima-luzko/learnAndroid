package com.example.todolist

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todolist.adapter.CategoryAdapter
import com.example.todolist.adapter.TasksAdapter
import com.example.todolist.data.Category
import com.example.todolist.data.Task
import com.example.todolist.room.AppDatabase
import com.example.todolist.room.dao.CategoryDao
import com.example.todolist.room.dao.SubTaskDao
import com.example.todolist.room.dao.TaskDao
import com.example.todolist.room.model.SubTask
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var categoryDao: CategoryDao
    private lateinit var taskDao: TaskDao
    private lateinit var subTaskDao: SubTaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI()
        getCurrentDayAndDateNumber()
        addToCategoryRecyclerView()
        //addToTaskRecyclerView()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todo_list.db"
        ).allowMainThreadQueries().build()

        categoryDao = db.categoryDao()
        taskDao = db.taskDao()
        subTaskDao = db.subTaskDao()

       // db.taskDao().insert(com.example.todolist.room.model.Task(0,"hah","4444", false,"11.02" ))
      //  db.taskDao().insert(com.example.todolist.room.model.Task(,"tttt","6666", false,"11.02" ))
        db.taskDao().delete(com.example.todolist.room.model.Task(1,"hah","4444", false,"11.02"))

        val all = db.taskDao().getAll()

        val recyclerView = findViewById<RecyclerView>(R.id.task_recycler_view)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = TasksAdapter(all)
            hasFixedSize()
        }
    }

    private fun addToCategoryRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.category_recycler_view)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = CategoryAdapter(categoryList())
            hasFixedSize()
        }
    }

//    private fun addToTaskRecyclerView() {
//        val recyclerView = findViewById<RecyclerView>(R.id.task_recycler_view)
//        with(recyclerView) {
//            layoutManager = LinearLayoutManager(
//                this@MainActivity,
//                LinearLayoutManager.VERTICAL,
//                false
//            )
//            adapter = TasksAdapter(taskList())
//            hasFixedSize()
//        }
//    }
//
//    private fun taskList() =
//        listOf(
//            Task(
//                icon = R.drawable.icon_homework,
//                taskName = "Помыть посуду"
//            ),
//            Task(
//                icon = R.drawable.icon_homework,
//                taskName = "Убраться в доме"
//            ),
//            Task(
//                icon = R.drawable.icon_workout,
//                taskName = "Покачать пресс"
//            ),
//            Task(
//                icon = R.drawable.icon_traveling,
//                taskName = "Сходить в лес за грибами"
//            )
//        )

    private fun categoryList() =
        listOf(
            Category(
                color = R.color.teal_700,
                icon = R.drawable.icon_design,
                name = "Design",
                totalTask = 5
            ),
            Category(
                color = R.color.crimson,
                icon = R.drawable.icon_book,
                name = "Learning",
                totalTask = 1
            ),
            Category(
                color = R.color.orange,
                icon = R.drawable.icon_homework,
                name = "Homework",
                totalTask = 10
            ),
            Category(
                color = R.color.indigo,
                icon = R.drawable.icon_traveling,
                name = "Traveling",
                totalTask = 0
            ),
            Category(
                color = R.color.green,
                icon = R.drawable.icon_workout,
                name = "Workout",
                totalTask = 3
            )
        )

    private fun getCurrentDayAndDateNumber() {
        val currentDate = findViewById<TextView>(R.id.date_number)
        val currentDay = findViewById<TextView>(R.id.day)
        currentDay.text = android.text.format.DateFormat.format("EEEE", Calendar.getInstance())
        currentDate.text = android.text.format.DateFormat.format("dd", Calendar.getInstance())
    }

    @Suppress("DEPRECATION")
    private fun hideSystemUI() {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R) {
            window.decorView.apply {
                systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }
}