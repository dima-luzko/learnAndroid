package com.example.colors

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var colorsList: ArrayList<Color>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideSystemUI()
        addToRecyclerView()
    }

    private fun addToRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = GridLayoutManager(
            applicationContext,
            3,
            LinearLayoutManager.VERTICAL,
            false
        )
        colorsList = colorsList()
        recyclerView.adapter = ColorsAdapter(applicationContext, colorsList!!)

    }

    private fun colorsList(): ArrayList<Color> {
        val colorForm = resources.getIntArray(R.array.colors_form)
        val colorsName = resources.getStringArray(R.array.colors_name)
        val itemsColor: ArrayList<Color> = ArrayList()
        for (i in colorForm.indices) {
            itemsColor.add(Color(colorForm = colorForm[i], colorName = colorsName[i]))
        }
        return itemsColor
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
}

