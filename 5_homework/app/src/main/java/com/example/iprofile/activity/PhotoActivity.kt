package com.example.iprofile.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iprofile.data.Comments
import com.example.iprofile.adapter.CommentsAdapter
import com.example.iprofile.R
import com.makeramen.roundedimageview.RoundedImageView

class PhotoActivity : AppCompatActivity() {

    private var isFirsPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        hideSystemUI()
        addToRecyclerView()
        visibleComments()
        addBookMark()
        addLike()
        commentsValue()
        setImage()
    }

    private fun setImage() {
        val photo = intent.getSerializableExtra(KEY_IMAGE)
        findViewById<RoundedImageView>(R.id.photo_in_photo_activity).setImageResource(photo as Int)
    }

    private fun commentsValue() {
        val commentsValue = findViewById<TextView>(R.id.comments_total)
        commentsValue.text = commentsList().size.toString()
    }

    private fun addLike() {
        val like = findViewById<ImageView>(R.id.likes)
        val likeTotal = findViewById<TextView>(R.id.likes_total)
        var totalCount: Int = 0
        totalCount = likeTotal.text.toString().toInt()
        like.setOnClickListener {
            if (isFirsPressed) {
                totalCount += 1
                like.setImageResource(R.drawable.icon_like_full)
                likeTotal.text = totalCount.toString()
                isFirsPressed = false
            } else {
                totalCount -= 1
                like.setImageResource(R.drawable.icon_like_in_photo_activity)
                likeTotal.text = totalCount.toString()
                isFirsPressed = true
            }
        }
    }

    private fun addBookMark() {
        val bookMark = findViewById<ImageView>(R.id.bookmark)
        bookMark.setOnClickListener {
            if (isFirsPressed) {
                bookMark.setImageResource(R.drawable.icon_bookmark)
                isFirsPressed = false
            } else {
                bookMark.setImageResource(R.drawable.icon_bookmark_outline)
                isFirsPressed = true
            }
        }
    }

    private fun visibleComments() {
        val comments = findViewById<ImageView>(R.id.comments)
        val visibilityComments = findViewById<RecyclerView>(R.id.recycler_view_in_photo_activity)
        comments.setOnClickListener {
            if (isFirsPressed) {
                visibilityComments.visibility = View.VISIBLE
                isFirsPressed = false
            } else {
                visibilityComments.visibility = View.INVISIBLE
                isFirsPressed = true
            }
        }
    }

    private fun addToRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_in_photo_activity)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@PhotoActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = CommentsAdapter(commentsList())
            hasFixedSize()
        }
    }

    companion object {
        private const val KEY_IMAGE = "Photo Image"
        fun getStartIntent(context: Context, photo: Int) =
            Intent(context, PhotoActivity::class.java).apply {
                putExtra(KEY_IMAGE, photo)
            }
    }

    private fun commentsList() =
        listOf(
            Comments(
                photo = R.drawable.nagiev_photo,
                name = getString(R.string.name_people_1),
                comments = getString(R.string.comment_people_1)
            ),
            Comments(
                photo = R.drawable.di_caprio_photo,
                name = getString(R.string.name_people_2),
                comments = getString(R.string.comment_people_2)
            )
        )

    @Suppress("DEPRECATION")
    private fun hideSystemUI() {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R) {
            window.decorView.apply {
                systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }
}