package com.jovita.mynews

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class NewsViewModel: ViewModel() {

    lateinit var newsList : List<Result>

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsdata.io/api/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiServices::class.java)

    suspend fun getNews(): List<Result>? = coroutineScope {
     val job = launch {
            val response = apiService.getNews()
            if(response.isSuccessful){
                newsList= response.body()?.results!!
            }else{
                null
            }
     }
        job.join()
        return@coroutineScope newsList
    }
}