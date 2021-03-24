package com.example.iprofile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iprofile.R
import com.example.iprofile.data.Comments
import de.hdodenhof.circleimageview.CircleImageView

class CommentsAdapter(private val commentsList: List<Comments>) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photos: CircleImageView = view.findViewById(R.id.photo_comments_people)
        val nameCommentsPeople: TextView = view.findViewById(R.id.name_comments_people)
        val commentsPeople: TextView = view.findViewById(R.id.comment_people)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comments_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = commentsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comments: Comments = commentsList[position]
        with(holder) {
            photos.setImageResource(comments.photo)
            nameCommentsPeople.text = comments.name
            commentsPeople.text = comments.comments
        }
    }
}