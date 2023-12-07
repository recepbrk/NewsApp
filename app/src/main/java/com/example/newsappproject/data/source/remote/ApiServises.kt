package com.example.newsappproject.data.source.remote

import com.example.newsappproject.data.model.ResponseTopHeadLine
import com.example.newsappproject.util.constants.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServises {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlineNews(
        @Query("country") country: String
    ): Response<ResponseTopHeadLine>

    @GET("v2/everything")
    suspend fun getEverythingNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Response<ResponseTopHeadLine>
}