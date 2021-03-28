package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.todolist.room.AppDatabase
import com.example.todolist.room.model.Task

class AddNewTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)
        hideSystemUI()

        val backIntent = Intent(this, MainActivity::class.java)
        val id: Int = intent.getIntExtra("CATEGORY_ID", 0)
        val pathImage: Int = intent.getIntExtra("CATEGORY_PATH_IMAGE", 0)
        val backgroundColor: String? = intent.getStringExtra("CATEGORY_BACKGROUND_COLOR")
        val taskName = findViewById<EditText>(R.id.add_new_task_in_edit_text)
        val buttonSave = findViewById<AppCompatButton>(R.id.button_save)

        buttonSave.setOnClickListener {
            val getAddText: String = taskName.text.toString()
            val datePicker = findViewById<DatePicker>(R.id.date)
            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1
            val year = datePicker.year
            val fullDate = "$day.$month.$year"

            AppDatabase.getInstance(this).taskDao().insert(
                Task(
                    title = getAddText,
                    completed = false,
                    date = fullDate,
                    pathImage = pathImage,
                    categoryId = id,
                    backgroundColor = backgroundColor
                )
            )
            startActivity(backIntent)
        }
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