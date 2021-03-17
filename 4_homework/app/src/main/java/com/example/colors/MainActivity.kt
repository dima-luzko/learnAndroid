package com.example.colors

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideSystemUI()
        addToRecyclerView()

    }

    private fun addToRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        with(recyclerView) {
            layoutManager = GridLayoutManager(
                this@MainActivity,
                3,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = ColorsAdapter(colorsList()){
                showDialog()
            }
        }
    }

    private fun showDialog() {
        val colorsName = resources.getStringArray(R.array.colors_name)
        val colorForm = resources.getIntArray(R.array.colors_form)

        val dialog = Dialog(this@MainActivity)
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.popup_window)
        val view = dialog.findViewById(R.id.colors_dialog_form) as View
        val body = dialog.findViewById(R.id.colors_dialog_name) as TextView
        for (i in colorForm.indices) {
            view.setBackgroundColor(colorForm[i])
            body.text = colorsName[i]
        }

        val button = dialog.findViewById(R.id.messageButton) as Button
        button.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun colorsList(): List<Color> {
        val colorForm = resources.getIntArray(R.array.colors_form)
        val colorsName = resources.getStringArray(R.array.colors_name)

        return colorForm.mapIndexed { index, colorForm ->
            Color(
                colorForm = colorForm,
                colorName = colorsName[index]
            )
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
}

