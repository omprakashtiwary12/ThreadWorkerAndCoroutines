package com.example.threadworkerandcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.threadworkerandcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.net.URL
import java.nio.charset.Charset
import kotlin.concurrent.thread
import kotlin.math.log
val fileUrl = "https://2833069.youcanlearnit.net/lorem_ipsum.txt"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(binding.root)

        // Initialize button click
        with(binding){
            runButton.setOnClickListener {
                runCode()
            }
            clearButton.setOnClickListener {
               viewModel.cancelJob()
            }
        }
        viewModel.myData.observe(this, { txtData ->
            log(txtData)
        })
    }


    /**
     * Run some code
     */
    private fun runCode() {
      /*  // Use handler with post delay
        Handler().postDelayed ({ log("Synchronous operation1")},2000 )
        Handler().postDelayed ({ log("Synchronous operation2")},1000 )
        Handler().postDelayed ({ log("Synchronous operation3")},3000 )

       // Use thread with sleep

        thread(start = true) {
          for (i in 1..10){
              Log.i(LOG_TAG, "Looping value $i")
              Thread.sleep(1000)
          }
          Log.i(LOG_TAG,"completed")
        }*/
      /*  CoroutineScope(Dispatchers.Main).launch {
            val result = fetchSomething()
            log(result ?: "Failed to fetch request from server")*/

       // }
        clearOutput()
     viewModel.doWork()
    }

    /**
     * Clear log display
     */
    private fun clearOutput() {
        binding.logDisplay.text = ""
        scrollTextToEnd()
    }

    /**
     * Log output to logcat and the screen
     */
    @Suppress("SameParameterValue")
    private fun log(message: String) {
        Log.i(LOG_TAG, message)
        binding.logDisplay.append(message + "\n")
        scrollTextToEnd()
    }

    /**
     * Scroll to end. Wrapped in post() function so it's the last thing to happen
     */
    private fun scrollTextToEnd() {
        Handler().post { binding.scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }


}