package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nitkkr.sanjay.expendableRecyclerview.homeScreen.NewsItem

class HomeActivityVM : ViewModel() {

    private val _fetchedResponse = MutableLiveData<ArrayList<NewsItem>>()
    val fetchedData: LiveData<ArrayList<NewsItem>>
        get() = _fetchedResponse

    init {
        _fetchedResponse.value = ArrayList()
        fetchNews()
    }

    private fun fetchNews() {
        for (i in 1..10) {
            _fetchedResponse.value!!.add(
                NewsItem(
                    100000008232540 + i,
                    "As Tanks Rolled Into Ukraine, So Did Malware. Then Microsoft Entered the War.",
                    "New York Times",
                    "After years of talks about the need for public-private partnerships to combat cyberattacks, the war in Ukraine is stress-testing the system."
                )
            )
        }
    }
}