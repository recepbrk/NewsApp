package com.example.newsappproject.data.source.remote

import com.example.newsappproject.data.model.ResponseTopHeadLine
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServises {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlineNews(@Query("country") country: String): Response<ResponseTopHeadLine>
}