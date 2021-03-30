package com.example.giphy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giphy.R
import com.example.giphy.data.Gif
import com.squareup.picasso.Picasso
import pl.droidsonroids.gif.GifImageView

class GifAdapter(private val gifList: List<Gif>) : RecyclerView.Adapter<GifAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gif: GifImageView = view.findViewById(R.id.gif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = gifList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = gifList[position]

        Picasso.get().load(item.gif).into(holder.gif)
        // holder.gif.setImageResource(item.gif)
    }
}