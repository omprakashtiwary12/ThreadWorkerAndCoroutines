package com.example.threadworkerandcoroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.net.URL
import java.nio.charset.Charset

class MainViewModel:ViewModel() {

    val myData = MutableLiveData<String>()
    private lateinit var job:Job
    fun doWork(){
       job =  viewModelScope.launch {
            myData.value = fetchSomething()
        }
    }
    public fun cancelJob(){
        try {
            job.cancel()
            myData.value = "Job cancelled"
        } catch (ignore: UninitializedPropertyAccessException) {
        }
    }
    private suspend fun fetchSomething(): String? {
        return withContext(Dispatchers.IO){
            delay(3000)
            val url = URL(fileUrl)
            return@withContext url.readText(Charset.defaultCharset())
        }
    }
}