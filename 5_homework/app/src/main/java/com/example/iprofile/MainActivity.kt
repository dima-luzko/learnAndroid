package com.example.iprofile

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
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


    private fun photosList(): ArrayList<Photo> {
       // val photo = resources.getIntArray(R.array.photos)
        val itemsPhoto: ArrayList<Photo> = ArrayList()
        itemsPhoto.add(Photo(photo_id = R.drawable.first_photo))
        itemsPhoto.add(Photo(photo_id = R.drawable.second_photo))
        itemsPhoto.add(Photo(photo_id = R.drawable.third_photo))
        itemsPhoto.add(Photo(photo_id = R.drawable.four_photo))
        itemsPhoto.add(Photo(photo_id = R.drawable.five_photo))
        itemsPhoto.add(Photo(photo_id = R.drawable.six_photo))
        itemsPhoto.add(Photo(photo_id = R.drawable.seven_photo))
        itemsPhoto.add(Photo(photo_id = R.drawable.eight_photo))
        itemsPhoto.add(Photo(photo_id = R.drawable.nine_photo))
        return itemsPhoto

//        with(resources.obtainTypedArray(R.array.photos)) {
//            Photo(photo_id = this.getResourceId(0,0))
//            recycle()
//        }

//        return photoItem.mapIndexed { _, photo ->
//            Photo(
//                photo_id = photo
//            )
//        }


    }

    private fun hideBackGroundInBottomNavigation () {
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