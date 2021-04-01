package com.example.giphy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy.R
import com.example.giphy.data.Gif

class GifAdapter(private val gifList: List<Gif>) : RecyclerView.Adapter<GifAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gifImageList: ImageView = view.findViewById(R.id.gif)
        val name: TextView = view.findViewById(R.id.gif_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = gifList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = gifList[position]
        Glide.with(holder.gifImageList.context).asGif().load(item.gifUrl).into(holder.gifImageList)
        // holder.name.text = item.name
    }
}