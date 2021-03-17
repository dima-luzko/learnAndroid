package com.example.colors

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ColorsAdapter(var context:Context, private val colorsList: ArrayList<Color>) :
    RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var colorsForms: View = view.findViewById<View>(R.id.colors_form)
        var colorsName: TextView = view.findViewById<TextView>(R.id.colors_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.colors_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return colorsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color: Color = colorsList[position]
        holder.colorsForms.setBackgroundResource(R.drawable.colors_form)
        holder.colorsForms.background.setColorFilter(color.colorForm, PorterDuff.Mode.SRC_OVER)
        holder.colorsName.text = color.colorName
    }
}


