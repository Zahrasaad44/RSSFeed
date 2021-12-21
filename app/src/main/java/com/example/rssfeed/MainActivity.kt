package com.example.rssfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeed.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdaptor: FeedAdaptor
    private lateinit var feed: List<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        feed = listOf()

        recyclerAdaptor = FeedAdaptor(feed)
        binding.feedRV.adapter = recyclerAdaptor
        binding.feedRV.layoutManager = LinearLayoutManager(this)

        parseRSS()
    }

    private fun parseRSS() {
        // Background thread
        CoroutineScope(Dispatchers.IO).launch {
            val data = async {
                val parser = XMLParser()
                parser.parseRSS()
            }.await()
            try {
                // Main thread, UI related
                withContext(Dispatchers.Main) {
                    recyclerAdaptor.updateFeed(data)
                }
            }catch (e: Exception){
                Log.d("main", "Couldn't get data")
            }
        }
    }
}