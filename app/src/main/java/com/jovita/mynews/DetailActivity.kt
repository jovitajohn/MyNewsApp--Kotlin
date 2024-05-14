package com.jovita.mynews

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.jovita.mynews.databinding.ActivityDetailBinding
import com.jovita.mynews.models.Result
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // set toolbar as support action bar
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.apply {
            title = ""
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val news = intent.getSerializableExtra("news",Result::class.java)
        news?.let { loadData(it) }


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun loadData(news: Result){

        if(news.imageUrl!=null ) {
            Picasso.get().load(news.imageUrl).into(binding.imgIcon)
        }else {
            Picasso.get().load("https://picsum.photos/200").into(binding.imgIcon)
            //sample url "https://picsum.photos/200"
        }

        binding.txtTitle.text = news.title
        binding.txtTime.text = getTimeAgo(news.pubDate)
        binding.txtContent.text = if(news.content != null) news.content else  "No data Available!"

    }

    fun getTimeAgo(time : String): String{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val startTimeInMillis = dateFormat.parse(time).time
        Log.i("publish time", "before : "+time + " now : "+ TimeAgo.using(startTimeInMillis))
        return TimeAgo.using(startTimeInMillis)
    }
}