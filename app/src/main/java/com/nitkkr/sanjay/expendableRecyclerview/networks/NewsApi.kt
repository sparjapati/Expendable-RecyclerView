package com.nitkkr.sanjay.expendableRecyclerview.networks

object NewsApi {
    val apiService: ApiService by lazy {
        RetroFitProvider.getRetroFitClient().create(ApiService::class.java)
    }
}