package com.example.giphy

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphy.adapter.GifAdapter
import com.example.giphy.data.Data
import com.example.giphy.data.Gif
import com.example.giphy.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI()
        getMemGifList()
        addToRecyclerViewAsGridLayoutManager()
    }

    private fun getMemGifList() {
        recyclerView = findViewById(R.id.recycler_view)

        RetrofitClient.instance.getMemGifList()
            .enqueue(object : Callback<Data> {
                override fun onFailure(call: Call<Data>, t: Throwable) {
                }

                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    with(recyclerView) {
                        layoutManager = GridLayoutManager(
                            this@MainActivity,
                            3,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        adapter =
                            GifAdapter(response.body()?.data?.map { it.images?.original } as List<Gif>)
                        hasFixedSize()
                    }
                }
            })
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
                RetrofitClient.instance.getGifList(searchName = searchGif)
                    .enqueue(object : Callback<Data> {
                        override fun onFailure(call: Call<Data>, t: Throwable) {
                        }

                        override fun onResponse(call: Call<Data>, response: Response<Data>) {
                            with(recyclerView) {
                                layoutManager = GridLayoutManager(
                                    this@MainActivity,
                                    3,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                                adapter =
                                    GifAdapter(response.body()?.data?.map { it.images?.original } as List<Gif>)
                                hasFixedSize()
                            }
                        }
                    })
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



