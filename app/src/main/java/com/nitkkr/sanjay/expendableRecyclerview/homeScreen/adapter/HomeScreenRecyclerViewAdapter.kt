package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nitkkr.sanjay.expendableRecyclerview.databinding.NewsItemBinding
import com.nitkkr.sanjay.expendableRecyclerview.homeScreen.NewsItem

class HomeScreenRecyclerViewAdapter(private var currentList: ArrayList<NewsItem>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(currentList[position])

    }

    override fun getItemCount(): Int = currentList.size
    fun addAll(newList: ArrayList<NewsItem>?) {
        var curr = currentList
        if (newList != null)
            curr.addAll(newList)
        else
            curr = newList!!
        currentList = curr
    }

    fun submitList(newList: ArrayList<NewsItem>?) {
        if (newList != null) {
            currentList = newList
            notifyDataSetChanged()
        }
    }

}

class NewsViewHolder private constructor(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): NewsViewHolder {
            val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NewsViewHolder(binding)
        }
    }

    fun bind(data: NewsItem) {
        binding.data = data
    }
}

