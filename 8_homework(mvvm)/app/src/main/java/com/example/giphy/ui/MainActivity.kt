package com.example.giphy.ui

import android.app.Dialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.giphy.R
import com.example.giphy.data.entities.Gif
import com.example.giphy.databinding.ActivityMainBinding
import com.example.giphy.databinding.SharePopupWindowBinding
import com.example.giphy.ui.gifs.GifAdapter
import com.example.giphy.ui.gifs.GifViewModel
import com.example.giphy.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }

    private val viewModel by viewModel<GifViewModel>()
    private val apiKey = Constants.API_KEY
    private val defaultNameForMemList = Constants.DEFAULT_NAME_FOR_MEM_LIST
    private val limit = Constants.LIMIT
    private val language = Constants.LANG
    private val rating = Constants.RATING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    private fun getMemGifList() {
        viewModel.getMemGifList(
            apiKey = apiKey,
            defaultName = defaultNameForMemList,
            limit = limit,
            rating = rating,
            language = language
        )
        viewModel.gif.observe(this@MainActivity, Observer {
            with(binding.recyclerView) {
                layoutManager = GridLayoutManager(
                    this@MainActivity,
                    3,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = GifAdapter(it) {
                    showSharePopupDialog(it)
                }
                hasFixedSize()
            }
        })
    }

    private fun addToRecyclerViewAsGridLayoutManager() {
        binding.buttonSearch.setOnClickListener {
            val searchGif = binding.searchText.text.toString()
            val builder = AlertDialog.Builder(this)
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
                viewModel.getGifList(
                    apiKey = apiKey,
                    query = searchGif,
                    limit = limit,
                    rating = rating,
                    language = language
                )
                viewModel.gif.observe(this@MainActivity, Observer {
                    with(binding.recyclerView) {
                        layoutManager = GridLayoutManager(
                            this@MainActivity,
                            3,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        adapter = GifAdapter(it) {
                            showSharePopupDialog(it)
                        }
                        hasFixedSize()
                    }
                })
            }
        }
    }

    private fun showSharePopupDialog(gif: Gif) {
        val dialogBinding = SharePopupWindowBinding.inflate(LayoutInflater.from(this))
        val dialog = Dialog(this)
        dialog.setContentView(dialogBinding.root)
        val linkGif = gif.gifUrl
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, linkGif)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)

        Glide.with(this).load(gif.gifUrl).thumbnail(0.1f)
            .into(dialogBinding.gifInPopup)

        dialogBinding.shareButton.setOnClickListener {
            startActivity(shareIntent)
            dialog.dismiss()
        }
        dialog.show()
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



