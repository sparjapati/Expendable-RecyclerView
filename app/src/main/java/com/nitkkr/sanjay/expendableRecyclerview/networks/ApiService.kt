package com.nitkkr.sanjay.expendableRecyclerview.networks

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/svc/mostpopular/v2/viewed/1.json")
    fun getNews(@Query("api-key") apiKey: String = NetworkConstants.API_KEY): Call<NewsItem>
}