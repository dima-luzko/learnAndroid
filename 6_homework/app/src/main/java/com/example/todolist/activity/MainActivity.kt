package com.example.todolist.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.CategoryAdapter
import com.example.todolist.adapter.TasksAdapter
import com.example.todolist.room.AppDatabase
import com.example.todolist.room.model.Task
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

    private fun showDialog(task: Task) {
        val dialog = Dialog(this)

        with(dialog) {
            this.setCancelable(false)
            this.setContentView(R.layout.edit_task_popup_window)
        }

        val backgroundColor = dialog.findViewById<CardView>(R.id.color_dialog_in_task)
        val iconTask = dialog.findViewById<ImageView>(R.id.icon_for_task_in_update_screen)
        val taskTitle = dialog.findViewById<TextView>(R.id.text_tasks_in_update_screen)
        val updateTaskTitleButton = dialog.findViewById<ImageButton>(R.id.button_update_task_title)
        val newTaskTitle = dialog.findViewById<EditText>(R.id.edit_task_title)
        val getTaskParam = AppDatabase.getInstance(this@MainActivity).taskDao()

        backgroundColor.setCardBackgroundColor(Color.parseColor(task.backgroundColor))
        iconTask.setImageResource(task.pathImage!!)
        taskTitle.text = task.title
        updateTaskTitleButton.setOnClickListener {
            val getNewTaskTitle: String = newTaskTitle.text.toString()
            val builder = AlertDialog.Builder(this)
            if (getNewTaskTitle.isEmpty()){
                with(builder){
                    setTitle(getString(R.string.attention))
                    setIcon(R.drawable.attention)
                    setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
                    setCancelable(false)
                    create()
                    show()
                }
            } else {
                getTaskParam.update(task)
                dialog.dismiss()
            }

        }
        dialog.show()
    }


    private fun addToTaskRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.task_recycler_view)
        val intent = Intent(this, TaskDetailActivity::class.java)
        val getTaskParam = AppDatabase.getInstance(this@MainActivity).taskDao()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = TasksAdapter(
                getTaskParam.getAll(),
                deleteTask = {
                    getTaskParam.delete(it)
                },
                editTask = {
                    val dialog = Dialog(this@MainActivity)

                    with(dialog) {
                        this.setCancelable(false)
                        this.setContentView(R.layout.edit_task_popup_window)
                    }

                    val backgroundColor = dialog.findViewById<CardView>(R.id.color_dialog_in_task)
                    val iconTask = dialog.findViewById<ImageView>(R.id.icon_for_task_in_update_screen)
                    val taskTitle = dialog.findViewById<TextView>(R.id.text_tasks_in_update_screen)
                    val updateTaskTitleButton = dialog.findViewById<ImageButton>(R.id.button_update_task_title)
                    val newTaskTitle = dialog.findViewById<EditText>(R.id.edit_task_title)

                    backgroundColor.setCardBackgroundColor(Color.parseColor(it.backgroundColor))
                    iconTask.setImageResource(it.pathImage!!)
                    taskTitle.text = it.title
                    updateTaskTitleButton.setOnClickListener {
                        val getNewTaskTitle: String = newTaskTitle.text.toString()
                        val builder = AlertDialog.Builder(this@MainActivity)
                        if (getNewTaskTitle.isEmpty()){
                            with(builder){
                                setTitle(getString(R.string.attention))
                                setIcon(R.drawable.attention)
                                setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
                                setCancelable(false)
                                create()
                                show()
                            }
                        } else {
                            //getTaskParam.update()
                            dialog.dismiss()
                        }

                    }
                    dialog.show()
                    Toast.makeText(this@MainActivity,"click",Toast.LENGTH_LONG).show()
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