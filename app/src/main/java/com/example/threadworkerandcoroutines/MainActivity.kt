package com.example.threadworkerandcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ScrollView
import com.example.threadworkerandcoroutines.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize button click
        with(binding){
            runButton.setOnClickListener {
                runCode()
            }
            clearButton.setOnClickListener {
                clearOutput()
            }
        }
    }


    /**
     * Run some code
     */
    private fun runCode() {
        // Use handler with post delay
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
        }

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