package com.example.colors

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
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
            adapter = ColorsAdapter(colorsList()) {
                showDialog(it)
            }
        }
    }

    private fun showDialog(color: Color) {
        val dialog = Dialog(this@MainActivity)

        with(dialog) {
            setCancelable(false)
            setContentView(R.layout.popup_window)
        }

        val colorBackground = dialog.findViewById<View>(R.id.colors_dialog_form)
        val colorTextName = dialog.findViewById<TextView>(R.id.colors_dialog_name)
        val colorCode = dialog.findViewById<TextView>(R.id.colors_dialog_hash_code)
        val button = dialog.findViewById<AppCompatButton>(R.id.messageButton)

        colorBackground.setBackgroundColor(color.colorForm)
        colorTextName.text = color.colorName
        colorCode.text = color.colorHashCode
        button.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun colorsList(): List<Color> {
        val colorForm = resources.getIntArray(R.array.colors_form)
        val colorsName = resources.getStringArray(R.array.colors_name)
        val colorsHashCode = resources.getStringArray(R.array.colors_hash_code)

        return colorForm.mapIndexed { index, colorForm ->
            Color(
                colorForm = colorForm,
                colorName = colorsName[index],
                colorHashCode = colorsHashCode[index]
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

    @Suppress("DEPRECATION")
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.R && hasFocus) {
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

