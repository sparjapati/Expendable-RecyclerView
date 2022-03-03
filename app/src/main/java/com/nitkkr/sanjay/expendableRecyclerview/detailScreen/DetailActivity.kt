package com.nitkkr.sanjay.expendableRecyclerview.detailScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nitkkr.sanjay.expendableRecyclerview.databinding.ActivityDetailBinding
import com.nitkkr.sanjay.expendableRecyclerview.networks.ResultsItem
import com.nitkkr.sanjay.expendableRecyclerview.utils.Constants
import com.nitkkr.sanjay.expendableRecyclerview.utils.imageSrc

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val news: ResultsItem = intent.extras?.get(Constants.HOME_ACTIVITY_INTENT) as ResultsItem

        if (news.media?.size!! > 0 && news.media[0]!!.type == "image" && news.media[0]!!.mediaMetadata?.size!! > 0)
            news.media[0]!!.mediaMetadata?.get(0)?.url?.let { binding.ivLogo.imageSrc(it) }
        binding.tvTitle.text = news.title.toString()
        binding.tvDesc.text = news.abstract

    }
}