package com.example.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.CategoryAdapter
import com.example.todolist.adapter.TasksAdapter
import com.example.todolist.room.AppDatabase
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI()
        getCurrentDayAndDateNumber()
        addToCategoryRecyclerView()
        addToTaskRecyclerView()

    }


    private fun addToCategoryRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.category_recycler_view)
        val intent = Intent(this, AddNewTaskActivity::class.java)
        val categoryList = AppDatabase.getInstance(this@MainActivity).categoryDao().getAll()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter =
                CategoryAdapter(categoryList) {
                    intent.putExtra("CATEGORY_ID", it.uid)
                    intent.putExtra("CATEGORY_PATH_IMAGE", it.pathImage)
                    intent.putExtra("CATEGORY_BACKGROUND_COLOR", it.backgroundColor)
                    startActivity(intent)
                }
            hasFixedSize()
        }
    }


    private fun addToTaskRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.task_recycler_view)
        val intent = Intent(this, TaskDetailActivity::class.java)
        val getData = AppDatabase.getInstance(this@MainActivity).taskDao()
        val taskList = AppDatabase.getInstance(this@MainActivity).taskDao().getAll()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = TasksAdapter(
                taskList,
                deleteClick = {
                    getData.delete(it)
                },
                click = {
                    intent.putExtra("TASK_ID", it.uid)
                    startActivity(intent)
                })

            hasFixedSize()
        }
    }


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