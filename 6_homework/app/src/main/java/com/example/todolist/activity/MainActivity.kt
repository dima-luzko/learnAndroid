package com.example.todolist.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.CategoryAdapter
import com.example.todolist.adapter.TasksAdapter
import com.example.todolist.room.AppDatabase
import com.example.todolist.room.model.SubTask
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
                    val categoryId = it.uid
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
        val getTaskParam = AppDatabase.getInstance(this@MainActivity).taskDao()
        val dialog = Dialog(this@MainActivity)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = TasksAdapter(
                getTaskParam.getAll(),
                addSubTask = {
                    with(dialog) {
                        this.setCancelable(false)
                        this.setContentView(R.layout.add_sub_task_popup_window)
                    }

                    val backgroundColorInSubTaskScreen =
                        dialog.findViewById<CardView>(R.id.color_dialog_in_sub_task)
                    val iconTaskInSubTaskScreen =
                        dialog.findViewById<ImageView>(R.id.icon_for_task_in_sub_task_screen)
                    val taskTitleInSubTaskScreen =
                        dialog.findViewById<TextView>(R.id.text_tasks_in_sub_task_screen)
                    val addSubTaskTitleButton =
                        dialog.findViewById<ImageButton>(R.id.button_add_sub_task_title)
                    val addSubTaskTitle = dialog.findViewById<EditText>(R.id.add_sub_task_title)
                    val taskId = it.uid

                    backgroundColorInSubTaskScreen.setCardBackgroundColor((Color.parseColor(it.backgroundColor)))
                    iconTaskInSubTaskScreen.setImageResource(it.pathImage!!)
                    taskTitleInSubTaskScreen.text = it.title

                    addSubTaskTitleButton.setOnClickListener {
                        val getSubTaskTitle: String = addSubTaskTitle.text.toString()
                        val builder = AlertDialog.Builder(this@MainActivity)
                        if (getSubTaskTitle.isEmpty()) {
                            with(builder) {
                                setTitle(getString(R.string.attention))
                                setIcon(R.drawable.attention)
                                setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
                                setCancelable(false)
                                create()
                                show()
                            }
                        } else {
                            AppDatabase.getInstance(this@MainActivity).subTaskDao().insert(
                                SubTask(
                                    title = getSubTaskTitle,
                                    taskId = taskId
                                )
                            )
                            dialog.dismiss()
                        }

                    }
                    dialog.show()
                },
                deleteTask = {
                    getTaskParam.delete(it)
                },
                editTask = {
                    with(dialog) {
                        this.setCancelable(false)
                        this.setContentView(R.layout.edit_task_popup_window)
                    }

                    val backgroundColor = dialog.findViewById<CardView>(R.id.color_dialog_in_task)
                    val iconTask =
                        dialog.findViewById<ImageView>(R.id.icon_for_task_in_update_screen)
                    val taskTitle = dialog.findViewById<TextView>(R.id.text_tasks_in_update_screen)
                    val updateTaskTitleButton =
                        dialog.findViewById<ImageButton>(R.id.button_update_task_title)
                    val newTaskTitle = dialog.findViewById<EditText>(R.id.edit_task_title)

                    backgroundColor.setCardBackgroundColor(Color.parseColor(it.backgroundColor))
                    iconTask.setImageResource(it.pathImage!!)
                    taskTitle.text = it.title
                    updateTaskTitleButton.setOnClickListener {
                        val getNewTaskTitle: String = newTaskTitle.text.toString()
                        val builder = AlertDialog.Builder(this@MainActivity)
                        if (getNewTaskTitle.isEmpty()) {
                            with(builder) {
                                setTitle(getString(R.string.attention))
                                setIcon(R.drawable.attention)
                                setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
                                setCancelable(false)
                                create()
                                show()
                            }
                        } else {
                            taskTitle.text = getNewTaskTitle
                            // getTaskParam.update(it)
                            dialog.dismiss()
                        }

                    }
                    dialog.show()
                },
                click = {
                    intent.putExtra("TASK_ID", it.uid)
                    startActivity(intent)
                })

            hasFixedSize()
        }
    }

    private fun getCurrentDayAndDateNumber() {
        val date = findViewById<TextView>(R.id.date_number)
        val day = findViewById<TextView>(R.id.day)
        day.text = android.text.format.DateFormat.format("EEEE", Calendar.getInstance())
        date.text = android.text.format.DateFormat.format("dd", Calendar.getInstance())
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