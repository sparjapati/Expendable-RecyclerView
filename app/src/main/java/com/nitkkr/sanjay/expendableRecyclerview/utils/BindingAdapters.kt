package com.nitkkr.sanjay.expendableRecyclerview.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nitkkr.sanjay.expendableRecyclerview.R

@BindingAdapter("img_src")
fun ImageView.imageSrc(url: String) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error).into(this)
}