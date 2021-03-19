package com.example.colors.adapter


import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.colors.R
import com.example.colors.data.Color


class ColorsAdapter(private val colorsList: List<Color>, val click: (Color) -> Unit) :
    RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var colorsForms: ImageView = view.findViewById(R.id.colors_form)
        var colorsName: TextView = view.findViewById(R.id.colors_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.colors_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = colorsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color: Color = colorsList[position]
        with(holder) {
            colorsForms.setBackgroundResource(R.drawable.colors_form)
            (colorsForms.drawable as? GradientDrawable)?.setColor(color.colorForm)
            colorsName.text = color.colorName
            colorsForms.setOnClickListener {
                click(color)
            }
        }
    }
}


