package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nitkkr.sanjay.expendableRecyclerview.databinding.NewsItemBinding
import com.nitkkr.sanjay.expendableRecyclerview.networks.ResultsItem
import com.nitkkr.sanjay.expendableRecyclerview.utils.imageSrc

class HomeScreenRecyclerViewAdapter : ListAdapter<ResultsItem, RecyclerView.ViewHolder>(Callbacks) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder.from(parent)
    }

    fun addAll(newList: ArrayList<ResultsItem?>?) {
        val curr = ArrayList(currentList)
        if (newList != null)
            curr.addAll(newList)
        submitList(curr)
    }

    override fun submitList(list: List<ResultsItem?>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsViewHolder)
            holder.bind(currentList[position])
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

object Callbacks : DiffUtil.ItemCallback<ResultsItem>() {
    override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem == newItem
    }
}


