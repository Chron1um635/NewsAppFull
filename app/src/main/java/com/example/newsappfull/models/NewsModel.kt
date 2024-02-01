package com.example.newsappfull.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsappfull.API.NewsAPI
import com.example.newsappfull.data.NewsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsModel: ViewModel() {
    val news: MutableLiveData<MutableList<NewsData>> by lazy {
        MutableLiveData<MutableList<NewsData>>()
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val newsAPI = retrofit.create(NewsAPI::class.java)
    private var newsPage: Int = 1
    fun getNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = newsAPI.getNews()
            launch(Dispatchers.Main) {
                news.value = list.articles
            }
        }
    }

    fun addNews() {
        newsPage++
        CoroutineScope(Dispatchers.IO).launch {
            val list = newsAPI.getNews(newsPage)
            launch(Dispatchers.Main) {
                news.value?.addAll(list.articles)
            }
        }
    }
}