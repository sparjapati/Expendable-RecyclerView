package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nitkkr.sanjay.expendableRecyclerview.R
import com.nitkkr.sanjay.expendableRecyclerview.databinding.ActivityHomeBinding
import com.nitkkr.sanjay.expendableRecyclerview.homeScreen.adapter.HomeScreenRecyclerViewAdapter
import com.nitkkr.sanjay.expendableRecyclerview.homeScreen.adapter.RecyclerViewOnItemClickListener
import com.nitkkr.sanjay.expendableRecyclerview.homeScreen.viewModel.HomeActivityVM
import com.nitkkr.sanjay.expendableRecyclerview.networks.ResultsItem
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
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshNews()
        }
        viewModel = ViewModelProvider(this)[HomeActivityVM::class.java]
    }

    private fun setAdapter() {
        adapter = HomeScreenRecyclerViewAdapter(object : RecyclerViewOnItemClickListener<ResultsItem> {
            override fun onMainViewClicked(obj: ResultsItem) {
            }

            override fun expendItem(position: Int) {
                adapter.setExpendable(position, true)
            }

            override fun collapseItem(position: Int) {
                adapter.setExpendable(position, false)
            }


        })
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
                    else
                        binding.swipeRefresh.isRefreshing = true
                }
                Status.SUCCESS -> {
                    binding.ivStatusImage.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false
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