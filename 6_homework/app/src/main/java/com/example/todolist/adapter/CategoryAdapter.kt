package com.example.todolist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.room.model.Category

class CategoryAdapter(
    private val categoryList: List<Category>,
    private val click: (Category) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val color: CardView = view.findViewById(R.id.card_category)
        val icon: ImageView = view.findViewById(R.id.category_icon)
        val nameCategory: TextView = view.findViewById(R.id.category_name)
        val buttonAddNewTask: ImageView = view.findViewById(R.id.add_new_task)
        val total: TextView = view.findViewById(R.id.total_tasks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = categoryList[position]

        //category.pathImage?.let { Picasso.get().load(it).into(holder.icon) }
        with(holder) {
            icon.setImageResource(category.pathImage!!)
            nameCategory.text = category.name
            color.setCardBackgroundColor((Color.parseColor(category.backgroundColor)))
            buttonAddNewTask.setOnClickListener {
                click(category)
            }
        }


    }
}