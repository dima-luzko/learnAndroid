package com.example.todolist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.component1
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.todolist.R
import com.example.todolist.room.model.Task
import kotlin.collections.ArrayList


class TasksAdapter(
    private val taskList: List<Task>,
    private val deleteTask: (Task) -> Unit,
    private val editTask: (Task) -> Unit,
    private val addSubTask: (Task) -> Unit,
    private val click: (Task) -> Unit
) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.icon_for_task)
        val taskName: TextView = view.findViewById(R.id.text_tasks)
        val taskButton: CardView = view.findViewById(R.id.task_category)
        val editButton: TextView = view.findViewById(R.id.edit_button)
        val deleteButton: TextView = view.findViewById(R.id.delete_button)
        val addSubTaskButton: TextView = view.findViewById(R.id.add_sub_task_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    private fun delete(task: Task) {
        val pos = taskList.indexOf(task)
        (taskList as ArrayList<Task>).removeAt(pos)
        notifyItemRemoved(pos)
    }

//    private fun update( newTaskTitle: String) {
//        val pos = taskList.indexOfFirst { it.title.equals(newTaskTitle, ignoreCase = true) }
//        notifyItemChanged(pos)
//    }

    private fun update(position: Int, newTaskTitle: String) {
        taskList[position].title = newTaskTitle
        notifyItemChanged(position)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task: Task = taskList[position]
        with(holder) {
            icon.setImageResource(task.pathImage!!)
            taskName.text = task.title
            taskButton.setCardBackgroundColor((Color.parseColor(task.backgroundColor)))
            taskButton.setOnClickListener {
                click(task)
            }
            deleteButton.setOnClickListener {
                delete(task)
                deleteTask(task)
            }
            editButton.setOnClickListener {
                //update(task.title!!)
                editTask(task)
            }
            addSubTaskButton.setOnClickListener {
                addSubTask(task)
            }

        }
    }
}

