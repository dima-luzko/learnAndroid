package com.example.phoneinfo.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.phoneinfo.R
import com.example.phoneinfo.utils.Constants
import java.io.File


class MemoryWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    private val memoryTitle: String = applicationContext.getString(R.string.memory_title)
    private val megabyte: String = applicationContext.getString(R.string.megabyte)
    private val memoryDescription: String = applicationContext.getString(
        R.string.memory_description,
        getFreeTotalMEmory().toString(),
        megabyte,
        getFullTotalMemory().toString(),
        megabyte
    )

    override fun doWork(): Result {
        createNotification(memoryTitle, memoryDescription)
        return Result.success()
    }

    private fun getFullTotalMemory(): Long {
        val totalSize: Long =
            File(applicationContext.filesDir.absoluteFile.toString()).totalSpace
        return totalSize / (1024 * 1024)

    }

    private fun getFreeTotalMEmory(): Long {
        val availTotalSize: Long =
            File(applicationContext.filesDir.absoluteFile.toString()).freeSpace
        return availTotalSize / (1024 * 1024)
    }

    private fun createNotification(title: String, description: String) {

        val notificationBuilder = NotificationCompat.Builder(
            applicationContext,
            Constants.BATTERY_NOTIFY_ID
        )
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.memory)
            .setColor(Color.GRAY)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        createNotificationChannel().notify(2, notificationBuilder.build())
    }

    private fun createNotificationChannel(): NotificationManager {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(
                    Constants.MEMORY_NOTIFY_ID,
                    Constants.MEMORY_CHANNEL_ID,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        return notificationManager
    }
}