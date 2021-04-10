package com.example.giphy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.giphy.R
import com.example.giphy.utils.Constants

class SplashScreenActivity : AppCompatActivity() {

    private val gifUrl = Constants.SPLASH_SCREEN_GIF_URL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        hideSystemUI()
        addGif()
        transitionIntoMainActivity()
    }

    private fun addGif() {
        val icon = findViewById<ImageView>(R.id.splash_screen_image)
        Glide.with(this)
            .load(gifUrl)
            .thumbnail(0.1f)
            .into(icon)
    }

    @Suppress("DEPRECATION")
    private fun transitionIntoMainActivity() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
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