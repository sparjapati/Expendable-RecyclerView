package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nitkkr.sanjay.expendableRecyclerview.networks.NewsApi
import com.nitkkr.sanjay.expendableRecyclerview.networks.NewsItem
import com.nitkkr.sanjay.expendableRecyclerview.networks.ResultsItem
import com.nitkkr.sanjay.expendableRecyclerview.utils.Constants.TAG
import edu.nitkkr.sanjay.postmanApi.utils.Response
import retrofit2.Call
import retrofit2.Callback

class HomeActivityVM : ViewModel() {

    private val _fetchedResponse = MutableLiveData<Response<ArrayList<ResultsItem?>>>()
    val fetchedData: LiveData<Response<ArrayList<ResultsItem?>>>
        get() = _fetchedResponse

    init {
        _fetchedResponse.value = Response.loading()
        fetchNews()
    }

    private fun fetchNews() {
        NewsApi.apiService.getNews().enqueue(object : Callback<NewsItem> {

            override fun onResponse(call: Call<NewsItem>, response: retrofit2.Response<NewsItem>) {
                Log.d(TAG, "onResponse: ${response.body()?.results!!.size ?: 0} items received")

                val resultItems = response.body()?.results ?: ArrayList()
                var curr = _fetchedResponse.value?.data
                if (curr != null)
                    curr.addAll(resultItems)
                else
                    curr = resultItems
                _fetchedResponse.value = Response.success(curr)
            }

            override fun onFailure(call: Call<NewsItem>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }
}