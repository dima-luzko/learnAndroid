package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.room.model.SubTask

class SubTaskAdapter(private val subTaskList: List<SubTask>) :
    RecyclerView.Adapter<SubTaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subTaskTitle: TextView = view.findViewById(R.id.text_sub_tasks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sub_task_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = subTaskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subTask: SubTask = subTaskList[position]
        holder.subTaskTitle.text = subTask.title
    }
}
