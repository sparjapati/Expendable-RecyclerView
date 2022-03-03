package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nitkkr.sanjay.expendableRecyclerview.R
import com.nitkkr.sanjay.expendableRecyclerview.databinding.NewsItemBinding
import com.nitkkr.sanjay.expendableRecyclerview.networks.ResultsItem
import com.nitkkr.sanjay.expendableRecyclerview.utils.Constants.TAG
import com.nitkkr.sanjay.expendableRecyclerview.utils.imageSrc

class HomeScreenRecyclerViewAdapter(private val clickListener: RecyclerViewOnItemClickListener<ResultsItem>) : ListAdapter<ResultsItem, RecyclerView.ViewHolder>(Callbacks) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder.from(parent)
    }

    fun addAll(data: ArrayList<ResultsItem?>?) {
        val list = ArrayList(currentList)
        data?.let { list.addAll(it).also { submitList(list) } }
    }

    override fun submitList(list: List<ResultsItem?>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsViewHolder)
            holder.bind(position, currentList[position], clickListener)
    }

    fun setExpendable(position: Int, value: Boolean) {
        val list = ArrayList(currentList)
        list[position].isExpended = value
        submitList(list)
        notifyItemChanged(position)
    }

}

class NewsViewHolder private constructor(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {

        private const val NO_EXPENDED = -1
        private var expendedItem = NO_EXPENDED
        fun from(parent: ViewGroup): NewsViewHolder {
            val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NewsViewHolder(binding)
        }
    }

    fun bind(position: Int, data: ResultsItem, clickListener: RecyclerViewOnItemClickListener<ResultsItem>) {
        binding.data = data
        if (data.media?.size!! > 0 && data.media[0]!!.type == "image" && data.media[0]!!.mediaMetadata?.size!! > 0)
            data.media[0]!!.mediaMetadata?.get(0)?.url?.let { binding.ivLogo.imageSrc(it) }
        binding.ivExpend.setImageResource(
            if (data.isExpended) R.drawable.ic_collapse else R.drawable.ic_expend
        )
        binding.tvDesc.visibility = if (data.isExpended) View.VISIBLE else View.GONE

        binding.vwMain.setOnClickListener {
            if (expendedItem == NO_EXPENDED) {
                Log.d(TAG, "bind: no item expend so expend $position item")
                clickListener.expendItem(position)
                expendedItem = position
            } else {
                if (position == expendedItem) {
                    Log.d(TAG, "bind: expended item clicked so collapse $expendedItem item")
                    clickListener.collapseItem(position)
                    expendedItem = NO_EXPENDED
                } else {
                    Log.d(
                        TAG,
                        "bind: different item clicked so collapse $expendedItem item and expend $position item"
                    )
                    clickListener.collapseItem(expendedItem)
                    clickListener.expendItem(position)
                    expendedItem = position
                }
            }
        }
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


