package com.example.phoneinfo.worker

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit


/**
 * Google's official solution there was to create a periodic work request with an "chain initiation" worker,
 * in that worker's doWork() define and enqueue a chain of one-time work requests.
 */
class PeriodicWork(context: Context, params: WorkerParameters) : Worker(context, params) {

    private val batteryRequest = OneTimeWorkRequestBuilder<BatteryWorker>().build()
    private val memoryRequest =
        OneTimeWorkRequestBuilder<MemoryWorker>().setInitialDelay(5, TimeUnit.SECONDS).build()

    override fun doWork(): Result {
        WorkManager.getInstance(applicationContext)
            .beginWith(batteryRequest)
            .then(memoryRequest)
            .enqueue()
        return Result.success()
    }
}