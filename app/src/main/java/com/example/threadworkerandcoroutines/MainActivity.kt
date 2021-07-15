package com.example.threadworkerandcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
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

    private lateinit var imageViews: Array<ImageView>
    private val drawables = arrayOf(
        R.drawable.die_1, R.drawable.die_2,
        R.drawable.die_3, R.drawable.die_4,
        R.drawable.die_5, R.drawable.die_6
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(binding.root)
        imageViews = arrayOf(binding.die1, binding.die2, binding.die3, binding.die4, binding.die5)
        viewModel.diceValue.observe(this, Observer {

            imageViews[it.first].setImageResource(drawables[it.second - 1])

        })

        binding.rollButton.setOnClickListener { viewModel.rollTheDice() }


    }
}