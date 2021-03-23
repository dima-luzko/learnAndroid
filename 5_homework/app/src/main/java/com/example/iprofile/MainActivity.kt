package com.example.iprofile

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        hideSystemUI()
        hideBackGroundInBottomNavigation()
        addToRecyclerView()

        val postsValue = findViewById<TextView>(R.id.posts_value)
        postsValue.text = photosList().size.toString()

    }

    override fun onStart() {
        super.onStart()
        val button = findViewById<AppCompatButton>(R.id.follow_button)
        val intent = Intent(this,PhotoActivity::class.java)
        button.setOnClickListener{
            startActivity(intent)
        }
    }

    private fun addToRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        with(recyclerView) {
            layoutManager = StaggeredGridLayoutManager(
                3,
                LinearLayoutManager.VERTICAL,
            )
            adapter = PhotoAdapter(photosList())
            hasFixedSize()
        }
    }

    private fun photosList() = listOf(
        R.drawable.first_photo,
        R.drawable.second_photo,
        R.drawable.third_photo,
        R.drawable.four_photo,
        R.drawable.five_photo,
        R.drawable.six_photo,
        R.drawable.seven_photo,
        R.drawable.eight_photo,
        R.drawable.nine_photo,
        R.drawable.ten_photo,
        R.drawable.eleven_photo,
        R.drawable.twelve_photo,
    )

    private fun hideBackGroundInBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        with(bottomNavigationView) {
            menu.getItem(2).isEnabled = false
            background = null
        }
    }

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