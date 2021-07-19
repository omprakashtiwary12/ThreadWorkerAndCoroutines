package com.example.threadworkerandcoroutines

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.net.URL
import java.nio.charset.Charset

class MyWorker(context: Context, params: WorkerParameters):
    Worker(context, params){
    override fun doWork(): Result {
        val url = URL(FILE_URL)
        val data = url.readText(Charset.defaultCharset())
        val result = workDataOf(DATA_KEY to data)
        return Result.success(result)
    }

  /*  val data = withContext(Dispatchers.IO) {
        setProgress(workDataOf(MESSAGE_KEY to "Doing some work"))
        delay(1000)
        setProgress(workDataOf(MESSAGE_KEY to "Doing some more work"))
        delay(1000)
        setProgress(workDataOf(MESSAGE_KEY to "Almost done"))
        delay(1000)
        val url = URL(FILE_URL)
        return@withContext url.readText(Charset.defaultCharset())
    }

    val result = workDataOf(DATA_KEY to data)
    return Result.success(result)*/
}