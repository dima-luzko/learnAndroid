package com.example.todolist.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.todolist.R
import com.example.todolist.room.AppDatabase

class TaskDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
        hideSystemUI()
        showTaskDetail()
    }

    override fun onStart() {
        super.onStart()
        val backIntent = Intent(this, MainActivity::class.java)
        val buttonComeBack = findViewById<AppCompatButton>(R.id.button_come_back)
        buttonComeBack.setOnClickListener {
            startActivity(backIntent)
        }
    }

    private fun showTaskDetail() {
        val taskId: Int = intent.getIntExtra("TASK_ID", 0)
        val taskIcon = findViewById<ImageView>(R.id.icon_for_task_in_description_screen)
        val taskName = findViewById<TextView>(R.id.title_tasks_in_description_screen)
        val taskDescription = findViewById<TextView>(R.id.description_task_in_description_screen)
        val getTaskParam = AppDatabase.getInstance(this).taskDao().findById(taskId)

        taskName.text = getTaskParam.title

        if (getTaskParam.description?.isEmpty()!!){
            taskDescription.text = getString(R.string.no_description_task)
        } else {
            taskDescription.text = getTaskParam.description
        }
        taskIcon.setImageResource(getTaskParam.pathImage!!)
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