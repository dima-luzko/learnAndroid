package com.example.phoneinfo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.phoneinfo.worker.PeriodicWork
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start() {
        val buttonStart = findViewById<Button>(R.id.button_start)
        val request = PeriodicWorkRequestBuilder<PeriodicWork>(15, TimeUnit.MINUTES).build()

        buttonStart.setOnClickListener {
            WorkManager.getInstance(applicationContext).enqueue(request)
        }
    }
}