package com.example.giphy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.giphy.adapter.GifAdapter
import com.example.giphy.retrofit.data.Gif

class MainActivity : AppCompatActivity() {

    private var isFirsPressed: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI()
        addToRecyclerViewAsGridLayoutManager()
        changeRecyclerViewManager()
    }

    private fun changeRecyclerViewManager() {
        val menuButton = findViewById<ImageView>(R.id.menu_button)
        menuButton.setOnClickListener {
            isFirsPressed = if (isFirsPressed) {
                menuButton.setImageResource(R.drawable.icon_black_grid_menu)
                addToRecyclerViewAsLinearLayoutManager()
                false
            } else {
                menuButton.setImageResource(R.drawable.icon_black_vertical_menu)
                addToRecyclerViewAsGridLayoutManager()
                true
            }
        }
    }

    private fun addToRecyclerViewAsGridLayoutManager() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        with(recyclerView) {
            layoutManager = StaggeredGridLayoutManager(
                3,
                LinearLayoutManager.VERTICAL
            )
            adapter = GifAdapter(gifList())
            hasFixedSize()
        }
    }

    private fun addToRecyclerViewAsLinearLayoutManager() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = GifAdapter(gifList())
            hasFixedSize()
        }
    }

    private fun gifList() = listOf(
        Gif(
            gif = R.drawable.dack
        ),
        Gif(
            gif = R.drawable.sun
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