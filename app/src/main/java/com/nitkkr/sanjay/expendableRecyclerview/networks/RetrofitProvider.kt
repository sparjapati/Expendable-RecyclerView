package com.nitkkr.sanjay.expendableRecyclerview.networks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitProvider {

    private val retroFitClient = Retrofit.Builder()
        .baseUrl(NetworkConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetroFitClient(): Retrofit = retroFitClient

}