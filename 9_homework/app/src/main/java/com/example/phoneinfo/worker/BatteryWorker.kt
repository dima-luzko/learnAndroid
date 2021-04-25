package com.example.phoneinfo.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.BatteryManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.phoneinfo.R
import com.example.phoneinfo.utils.Constants


class BatteryWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    private val batteryTitle: String = applicationContext.getString(R.string.battery_title)
    private val percent: String = applicationContext.getString(R.string.percent)
    private val batteryDescription: String =
        applicationContext.getString(
            R.string.battery_description,
            getBatteryLevel().toString(),
            percent
        )

    override fun doWork(): Result {
        createNotification(batteryTitle, batteryDescription)
        return Result.success()
    }

    private fun getBatteryLevel(): Int {
        val batteryManager =
            applicationContext.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }

    private fun createNotification(title: String, description: String) {

        val notificationBuilder = NotificationCompat.Builder(
            applicationContext,
            Constants.BATTERY_NOTIFY_ID
        )
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.battery)
            .setColor(Color.MAGENTA)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        createNotificationChannel().notify(1, notificationBuilder.build())
    }

    private fun createNotificationChannel(): NotificationManager {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(
                    Constants.BATTERY_NOTIFY_ID,
                    Constants.BATTERY_CHANNEL_ID,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        return notificationManager
    }
}