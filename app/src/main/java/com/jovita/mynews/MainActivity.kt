package com.jovita.mynews

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    lateinit var view : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
         view = findViewById(R.id.test)
         fetchNews()
    }

    private fun fetchNews() {
        lateinit var newsList : List<Result>
        val viewModelJob = Job()
        CoroutineScope(Dispatchers.Main + viewModelJob).launch  {
            withContext(Dispatchers.IO) {
                val job = launch { newsList = NewsViewModel().getNews()!! }
                job.join()
            }
            withContext(Dispatchers.Main) {
              loadUi(newsList)
            }
        }
    }

    fun loadUi(newsList : List<Result>){
        view.setText("count " + newsList.size)
    }
}