package com.example.todolist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.todolist.R
import com.example.todolist.room.dao.TaskDao
import com.example.todolist.room.model.Task
import kotlin.collections.ArrayList


class TasksAdapter(private val taskList: List<Task>, private val taskDao: TaskDao, private val click: (Task) -> Unit) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    private val viewBinderHelper = ViewBinderHelper()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.icon_for_task_in_description_screen)
        val taskName: TextView = view.findViewById(R.id.text_tasks_in_description_screen)
        val taskButton: CardView = view.findViewById(R.id.task_category)
        val swipe: SwipeRevealLayout = view.findViewById(R.id.swipe)

        //        val textEdit : TextView = view.findViewById(R.id.edit_button)
        val deleteButton: TextView = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    private fun deleteTask(item: Task) {
        val pos = taskList.indexOf(item)
        (taskList as ArrayList<Task>).removeAt(pos)
        notifyItemRemoved(pos)
        taskDao.delete(pos)
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
                deleteTask(task)
            }
        }
//        viewBinderHelper.bind(holder.swipe, task.uid.toString())
    }
}

