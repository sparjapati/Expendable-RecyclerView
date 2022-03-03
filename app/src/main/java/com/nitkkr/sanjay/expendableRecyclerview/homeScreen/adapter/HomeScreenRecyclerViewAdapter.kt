package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nitkkr.sanjay.expendableRecyclerview.databinding.NewsItemBinding
import com.nitkkr.sanjay.expendableRecyclerview.networks.ResultsItem
import com.nitkkr.sanjay.expendableRecyclerview.utils.imageSrc

class HomeScreenRecyclerViewAdapter(private var currentList: ArrayList<ResultsItem?>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        currentList[position]?.let { holder.bind(it) }

    }

    override fun getItemCount(): Int = currentList.size
    fun addAll(newList: ArrayList<ResultsItem?>?) {
        var curr = currentList
        if (curr != null && newList != null)
            curr.addAll(newList)
        else
            curr = newList!!
        currentList = curr
    }

    fun submitList(newList: ArrayList<ResultsItem?>?) {
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

    fun bind(data: ResultsItem) {
        binding.data = data
        if (data.media?.size!! > 0 && data.media[0]!!.type == "image" && data.media[0]!!.mediaMetadata?.size!! > 0)
            data.media[0]!!.mediaMetadata?.get(0)?.url?.let { binding.ivLogo.imageSrc(it) }
    }
}

