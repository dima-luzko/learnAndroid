package com.example.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.todolist.R
import com.example.todolist.room.AppDatabase
import com.example.todolist.room.model.Task

class AddNewTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)
        hideSystemUI()
    }

    override fun onStart() {
        super.onStart()
        addNewTask()
    }

    private fun addNewTask() {
        val id: Int = intent.getIntExtra("CATEGORY_ID", 0)
        val pathImage: Int = intent.getIntExtra("CATEGORY_PATH_IMAGE", 0)
        val backgroundColor: String? = intent.getStringExtra("CATEGORY_BACKGROUND_COLOR")
        val taskName = findViewById<EditText>(R.id.add_new_task_in_edit_text)
        val taskDescription = findViewById<EditText>(R.id.add_description_task_in_edit_text)
        val buttonSave = findViewById<AppCompatButton>(R.id.button_save)
        val intent = Intent(this,MainActivity::class.java)

        buttonSave.setOnClickListener {
            val getAddTaskName: String = taskName.text.toString()
            val getAddDescriptionTask = taskDescription.text.toString()
            val datePicker = findViewById<DatePicker>(R.id.date)
            val builder = AlertDialog.Builder(this)
            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1
            val year = datePicker.year
            val fullDate = "$day.$month.$year"

            if (getAddTaskName.isEmpty()) {
                with(builder) {
                    setTitle(getString(R.string.attention))
                    setIcon(R.drawable.attention)
                    setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
                    setCancelable(false)
                    create()
                    show()
                }
            } else {
                AppDatabase.getInstance(this).taskDao().insert(
                    Task(
                        title = getAddTaskName,
                        description = getAddDescriptionTask,
                        completed = false,
                        date = fullDate,
                        pathImage = pathImage,
                        categoryId = id,
                        backgroundColor = backgroundColor
                    )
                )
                startActivity(intent)
            }
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