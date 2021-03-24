package com.example.iprofile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.iprofile.R

class PhotoAdapter(private val photoList: List<Int>, private val click: (Int) -> Unit) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photos: ImageView = view.findViewById(R.id.photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = photoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = photoList[position]
        with(holder) {
            photos.setImageResource(item)
            photos.setOnClickListener {
                click(item)
            }
        }
    }
}