package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nitkkr.sanjay.expendableRecyclerview.databinding.ActivityHomeBinding
import com.nitkkr.sanjay.expendableRecyclerview.homeScreen.adapter.HomeScreenRecyclerViewAdapter
import com.nitkkr.sanjay.expendableRecyclerview.homeScreen.viewModel.HomeActivityVM

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
        adapter = HomeScreenRecyclerViewAdapter(viewModel.fetchedData.value?.results ?: ArrayList())
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNews.layoutManager = layoutManager
        binding.rvNews.adapter = adapter
    }

    private fun setObservers() {
        viewModel.fetchedData.observe(this) { newList ->
            adapter.submitList(newList.results)
        }
    }


}