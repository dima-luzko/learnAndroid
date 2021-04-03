package com.example.giphy.activity

import android.app.Dialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy.R
import com.example.giphy.adapter.GifAdapter
import com.example.giphy.data.Gif
import com.example.giphy.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        hideSystemUI()
        getMemGifList()
        addToRecyclerViewAsGridLayoutManager()
        changeTheme()
    }

    private fun changeTheme() {
        val changeTheme = findViewById<SwitchCompat>(R.id.change_theme_switch)
        changeTheme.setOnCheckedChangeListener { _, checked ->
            when (checked) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun showSharePopupDialog(gif: Gif) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.share_popup_window)
        val gifInPopup = dialog.findViewById<ImageView>(R.id.gif_in_popup)
        val shareButton = dialog.findViewById<AppCompatButton>(R.id.share_button)
        val linkGif = gif.gifUrl
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, linkGif)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)

        Glide.with(this).load(gif.gifUrl).thumbnail(0.1f)
            .into(gifInPopup)

        shareButton.setOnClickListener {
            startActivity(shareIntent)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun getMemGifList() {
        recyclerView = findViewById(R.id.recycler_view)

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.instance.getMemGifList()
            withContext(Dispatchers.Main) {
                with(recyclerView) {
                    layoutManager = GridLayoutManager(
                        this@MainActivity,
                        3,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    adapter = GifAdapter(response.data?.map { it.images?.original } as List<Gif>) {
                        showSharePopupDialog(it)
                    }
                    hasFixedSize()
                }
            }
        }
    }

    private fun addToRecyclerViewAsGridLayoutManager() {
        recyclerView = findViewById(R.id.recycler_view)
        val searchName = findViewById<EditText>(R.id.search_text)
        val searchButton = findViewById<ImageButton>(R.id.button_search)
        val builder = AlertDialog.Builder(this)

        searchButton.setOnClickListener {
            val searchGif = searchName.text.toString()
            if (searchGif.isEmpty()) {
                with(builder) {
                    setTitle(getString(R.string.attention))
                    setIcon(R.drawable.attention)
                    setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
                    setCancelable(false)
                    create()
                    show()
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val response = RetrofitClient.instance.getGifList(searchName = searchGif)
                    withContext(Dispatchers.Main) {
                        with(recyclerView) {
                            layoutManager = GridLayoutManager(
                                this@MainActivity,
                                3,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            adapter =
                                GifAdapter(response.data?.map { it.images?.original } as List<Gif>) {
                                    showSharePopupDialog(it)
                                }
                            hasFixedSize()
                        }
                    }
                }
            }
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



