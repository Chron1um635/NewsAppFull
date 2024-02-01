package com.example.newsappfull

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.newsappfull.data.NewsData
import com.example.newsappfull.databinding.ActivityNewsBinding
import com.example.newsappfull.models.NewsModel
import com.squareup.picasso.Picasso

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private val newsModel: NewsModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        Log.d("My", "${newsModel.news.value}")
    }

    fun init(){
        val news = intent.getSerializableExtra("data") as NewsData
        binding.apply {
            tvTitle.text = news.title
            tvDesc.text = news.description
            tvUrl.text = news.url
            tvDatePublish.text = news.publishedAt
            tvSourcePublish.text = news.source.name
            if (news.urlToImage?.isEmpty() == false) {
                Picasso.get().load(news.urlToImage).into(imNews)
            } else {
                imNews.setImageResource(R.drawable.no_image)
            }
            tvUrl.setOnClickListener {
                startActivity(Intent(this@NewsActivity, WebViewActivity::class.java).apply {
                    putExtra("url", news.url)
                })
            }
        }
    }
}