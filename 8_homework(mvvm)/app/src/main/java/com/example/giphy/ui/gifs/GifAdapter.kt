package com.example.giphy.ui.gifs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy.data.entities.Gif
import com.example.giphy.databinding.GifItemBinding

class GifAdapter(private val gifList: List<Gif>, private val click: (Gif) -> Unit) :
    RecyclerView.Adapter<GifAdapter.ViewHolder>() {

    class ViewHolder(val binding: GifItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GifItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = gifList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = gifList[position]
        Glide.with(holder.binding.gif.context).load(item.gifUrl).thumbnail(0.1f)
            .into(holder.binding.gif)
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            click(item)
        }
    }
}