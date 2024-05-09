package com.jovita.mynews

import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {


    @GET("news?apikey=pub_43678d097fd0f04720d2bd3dd8aaec62ffdf6&q=Android")
    suspend fun getNews(): Response<News>
    //private val apiKey = "pub_43678d097fd0f04720d2bd3dd8aaec62ffdf6"

}