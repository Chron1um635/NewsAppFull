package com.example.newsappfull

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappfull.API.NewsAPI
import com.example.newsappfull.data.NewsData
import com.example.newsappfull.databinding.ActivityMainBinding
import com.example.newsappfull.fragments.NewsFragment
import com.example.newsappfull.models.NewsModel
import com.example.newsappupdate.Adapters.NewsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NewsAdapter.Listener {
    lateinit var binding: ActivityMainBinding
    val newsModel: NewsModel by viewModels()
    private lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRcView()
    }

    private fun initRcView() = with(binding){
        adapter = NewsAdapter(this@MainActivity)
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = adapter
        rcView.addOnScrollListener(this@MainActivity.scrollListener)
        newsModel.getNews()
        newsModel.news.observe(this@MainActivity) {
            adapter.submitList(newsModel.news.value)
        }
    }
    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        var isLoading = false
        var isLastPage = false
        var isScrolling = false

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)


            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisisble = totalItemCount >= 20
            val shouldPaginate = isNotLoadingAndNotLastPage
                    && isAtLastItem && isNotAtBeginning
                    && isTotalMoreThanVisisble && isScrolling

            if (shouldPaginate) {
                newsModel.addNews()
                isScrolling = false
            }

        }
    }

    override fun onClick(newsItem: NewsData) {
        newsModel.news.observe(this){
            startActivity(Intent(this, NewsActivity::class.java).apply {
                putExtra("data", newsItem as NewsData)
            })
        }
    }
}