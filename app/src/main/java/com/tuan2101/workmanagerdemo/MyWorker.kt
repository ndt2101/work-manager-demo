package com.tuan2101.workmanagerdemo

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(var context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {
    var mediaPlayer: MediaPlayer? = null
    override fun doWork(): Result {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.bai2)
        }
        mediaPlayer!!.start()
        for (i in 0..100) {
            Log.i("aaa", i.toString())

            Thread.sleep(1000)
        }
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        if (mediaPlayer != null) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}