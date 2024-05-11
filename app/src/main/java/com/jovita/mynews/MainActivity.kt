package com.jovita.mynews

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jovita.mynews.adapter.NewsAdapter
import com.jovita.mynews.models.NewsViewModel
import com.jovita.mynews.models.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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

        val dataset = arrayOf("January", "February", "March")
        val customAdapter = NewsAdapter(dataset)

        val recyclerView: RecyclerView = findViewById(R.id.news_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
        customAdapter.notifyDataSetChanged()


    }
}