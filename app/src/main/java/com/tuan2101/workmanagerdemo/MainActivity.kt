package com.tuan2101.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.work.*
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workRequest: WorkRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
            .addTag("my_worker")
            .build()

        WorkManager
            .getInstance(applicationContext)
            .enqueueUniquePeriodicWork("Play Music", ExistingPeriodicWorkPolicy.KEEP,
                workRequest as PeriodicWorkRequest
            )

        val workInfo: ListenableFuture<MutableList<WorkInfo>> = WorkManager.getInstance(applicationContext).getWorkInfosByTag("my_worker")
        Toast.makeText(this, workInfo.toString(), Toast.LENGTH_LONG).show()
    }
}