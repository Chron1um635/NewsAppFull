package com.example.newsappfull.API

import com.example.newsappfull.data.ResponseObject
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/v2/everything?q=keyword&apiKey=dfe5b2f4813e4781a9f27647aa6fd1a7&pageSize=20")
    suspend fun getNews(
        @Query("page") page: Int = 1
    ): ResponseObject
}