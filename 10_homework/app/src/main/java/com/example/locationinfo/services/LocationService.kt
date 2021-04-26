package com.example.locationinfo.services

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.example.locationinfo.R
import com.example.locationinfo.utils.Constants
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices


class LocationService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        getLocation()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val latitude: Double = locationResult.lastLocation.latitude
            val longitude: Double = locationResult.lastLocation.longitude
            Log.d("LOCATION_UPDATE", "Latitude = $latitude, Longitude = $longitude")
            Toast.makeText(
                applicationContext,
                "Latitude = $latitude, Longitude = $longitude",
                Toast.LENGTH_LONG
            ).show()
            createNotification(
                getString(R.string.location_title),
                getString(R.string.location_description, latitude.toString(), longitude.toString())
            )
        }
    }

    private fun getLocation() {
        val locationRequest = LocationRequest.create().apply {
            interval = Constants.LOCATION_INTERVAL.toLong()
            fastestInterval = Constants.LOCATION_FASTER_INTERVAL.toLong()
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    private fun createNotification(title: String, description: String) {
        val notificationBuilder =
            NotificationCompat.Builder(applicationContext, Constants.LOCATION_NOTIFY_ID)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.location)
                .setColor(Color.GRAY)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        createNotificationChannel().notify(1, notificationBuilder.build())
    }

    private fun createNotificationChannel(): NotificationManager {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(
                    Constants.LOCATION_NOTIFY_ID,
                    Constants.LOCATION_CHANNEL_ID,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        return notificationManager
    }
}