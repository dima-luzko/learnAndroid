package com.example.todolist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.Tasks

class TasksAdapter(private val taskList: List<Tasks>) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.icon_for_task_in_description_screen)
        val taskName: TextView = view.findViewById(R.id.text_tasks_in_description_screen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task: Tasks = taskList[position]
        with(holder) {
            icon.setImageResource(task.icon)
            taskName.text = task.taskName
        }
    }
}