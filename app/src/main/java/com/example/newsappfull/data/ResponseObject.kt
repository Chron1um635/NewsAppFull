package com.example.newsappfull.data

import java.io.Serializable

data class ResponseObject(
    val status: String,
    val totalResult: Int,
    val articles: ArrayList<NewsData>
): Serializable
