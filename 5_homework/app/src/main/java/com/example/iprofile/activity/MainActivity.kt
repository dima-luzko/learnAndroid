package com.example.iprofile.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.iprofile.R
import com.example.iprofile.adapter.PhotoAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    companion object {
        private var sTheme = 0
        private val THEME_DEFAULT = 0
        private val THEME_MY_THEME = 1

        fun changeTheme(activity: Activity, theme: Int) {
            sTheme = theme
            activity.finish()
            activity.startActivity(Intent(activity, activity.javaClass))
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        fun onActivityCreateTheme(activity: Activity) {
            when (sTheme) {
                THEME_DEFAULT -> activity.setTheme(R.style.Theme_IProfile)
                THEME_MY_THEME -> activity.setTheme(R.style.Theme_Blue)
                else -> activity.setTheme(R.style.Theme_IProfile)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityCreateTheme(this)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        hideSystemUI()
        hideBackGroundInBottomNavigation()
        addToRecyclerView()
        totalPosts()
        addFollowers()

        val changeTheme = findViewById<ImageView>(R.id.menu)
        changeTheme.setOnClickListener {
            sTheme = if (sTheme == 0) {
                changeTheme(this, THEME_MY_THEME)
                1
            } else {
                changeTheme(this, THEME_DEFAULT)
                0
            }
        }
    }

    private fun addFollowers() {
        val buttonFollow = findViewById<AppCompatButton>(R.id.follow_button)
        val followTotal = findViewById<TextView>(R.id.followers_value)
        var totalCount: Int = 0

        totalCount = followTotal.text.toString().toInt()
        buttonFollow.setOnClickListener {
            totalCount += 1
            followTotal.text = totalCount.toString()
        }
    }

    private fun totalPosts() {
        val postsValue = findViewById<TextView>(R.id.posts_value)
        postsValue.text = photosList().size.toString()
    }

    private fun addToRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        with(recyclerView) {
            layoutManager = StaggeredGridLayoutManager(
                3,
                LinearLayoutManager.VERTICAL,
            )
            adapter = PhotoAdapter(photosList()) {
                startActivity(
                    PhotoActivity.getStartIntent(
                        context = this@MainActivity,
                        photo = it
                    )
                )
            }
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