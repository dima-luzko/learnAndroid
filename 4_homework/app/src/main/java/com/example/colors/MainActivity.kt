package com.example.colors

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var colorsList: ArrayList<Color>? = null
    private var colorsAdapter: ColorsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideSystemUI()

        recyclerView = findViewById(R.id.recycler_view)
        gridLayoutManager = GridLayoutManager(
            applicationContext,
            3,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView?.layoutManager = gridLayoutManager
        colorsList = ArrayList()
        colorsList = colorsList()
        colorsAdapter = ColorsAdapter(applicationContext, colorsList!!)
        recyclerView?.adapter = colorsAdapter
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

    private fun colorsList(): ArrayList<Color> {
        val colorForm = resources.getIntArray(R.array.colors_form)
        val colorsName = resources.getStringArray(R.array.colors_name)
        val itemsColor: ArrayList<Color> = ArrayList()
        itemsColor.add(Color(colorForm = colorForm.size, colorName = colorsName.size.toString()))

        return itemsColor
    }

}

