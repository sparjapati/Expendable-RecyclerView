package com.nitkkr.sanjay.expendableRecyclerview.homeScreen.adapter

interface RecyclerViewOnItemClickListener<T> {
    fun onMainViewClicked(obj: T)
    fun expendItem(position:Int)
    fun collapseItem(position:Int)
}