package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nitkkr.sanjay.expendableRecyclerview.R
import com.nitkkr.sanjay.expendableRecyclerview.databinding.ActivityHomeBinding
import com.nitkkr.sanjay.expendableRecyclerview.homeScreen.adapter.HomeScreenRecyclerViewAdapter
import com.nitkkr.sanjay.expendableRecyclerview.homeScreen.viewModel.HomeActivityVM
import com.nitkkr.sanjay.expendableRecyclerview.utils.Constants.TAG
import edu.nitkkr.sanjay.postmanApi.utils.Status

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeActivityVM
    private lateinit var adapter: HomeScreenRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUI()
        setAdapter()
        setObservers()
    }

    private fun setUI() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[HomeActivityVM::class.java]
    }

    private fun setAdapter() {
        adapter = HomeScreenRecyclerViewAdapter()
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNews.layoutManager = layoutManager
        binding.rvNews.adapter = adapter
    }

    private fun setObservers() {
        viewModel.fetchedData.observe(this) { resultItemsListResponse ->
            when (resultItemsListResponse.status) {
                Status.LOADING -> {
                    if (resultItemsListResponse.data == null || resultItemsListResponse.data.size == 0)
                        binding.ivStatusImage.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    Log.d(TAG, "setObservers: ${resultItemsListResponse.data?.size ?: 0} items submitting")
                    binding.ivStatusImage.visibility = View.GONE
                    adapter.addAll(resultItemsListResponse.data)
                }
                Status.ERROR -> {
                    if (resultItemsListResponse.data == null || resultItemsListResponse.data.size == 0)
                        binding.ivStatusImage.setImageResource(R.drawable.ic_status_error)
                }
            }
        }
    }


}