package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter =
                CategoryAdapter(AppDatabase.getInstance(this@MainActivity).categoryDao().getAll()) {
                    intent.putExtra("CATEGORY_ID",it.uid)
                    intent.putExtra("CATEGORY_PATH_IMAGE",it.pathImage)
                    intent.putExtra("CATEGORY_BACKGROUND_COLOR",it.backgroundColor)
                    startActivity(intent)
                }
            hasFixedSize()
        }
    }


    private fun addToTaskRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.task_recycler_view)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = TasksAdapter(AppDatabase.getInstance(this@MainActivity).taskDao().getAll())
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