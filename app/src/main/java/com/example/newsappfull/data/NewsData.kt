package com.example.newsappfull.data

import java.io.Serializable

data class NewsData(
    val source: SourceData,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String,
    var counter: Int
): Serializable
