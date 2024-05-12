package com.jovita.mynews.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jovita.mynews.R
import com.jovita.mynews.models.Result
import com.squareup.picasso.Picasso

class NewsAdapter(private val dataSet: List<Result>,private val appContext: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_list_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val news = dataSet[position]


        // sets the text to the textview from our itemHolder class
        holder.title.text = news.title
        holder.creator.text = news.creator?.get(0) ?: ""
        holder.time.text = news.pubDate

        if(news.imageUrl!=null )
                Picasso.get().load(news.imageUrl).into(holder.img)
       // Picasso.get().load("https://picsum.photos/200").into(holder.img)
       //sample url "https://picsum.photos/200"

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return dataSet.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val title: TextView = itemView.findViewById(R.id.txt_title)
        val creator : TextView = itemView.findViewById(R.id.txt_creator)
        val time : TextView = itemView.findViewById(R.id.txt_time)
        val img : ImageView = itemView.findViewById(R.id.img_icon)
    }
}
