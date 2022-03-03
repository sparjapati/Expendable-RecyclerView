package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nitkkr.sanjay.expendableRecyclerview.networks.NewsApi
import com.nitkkr.sanjay.expendableRecyclerview.networks.NewsItem
import com.nitkkr.sanjay.expendableRecyclerview.utils.Constants.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivityVM : ViewModel() {

    private val _fetchedResponse = MutableLiveData<NewsItem>()
    val fetchedData: LiveData<NewsItem>
        get() = _fetchedResponse

    init {
        fetchNews()
    }

    private fun fetchNews() {
        NewsApi.apiService.getNews().enqueue(object : Callback<NewsItem> {
            override fun onResponse(call: Call<NewsItem>, response: Response<NewsItem>) {
                Log.d(TAG, "onResponse: ${response.body()?.results!!.size ?: 0} items received")
                _fetchedResponse.value = response.body()
            }

            override fun onFailure(call: Call<NewsItem>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }
}