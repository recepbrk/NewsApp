package com.example.newsappproject.data.repository

import com.example.newsappproject.data.source.remote.ApiServises
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiServices: ApiServises) {

    suspend fun getTopHeadlineNews(country: String) = apiServices.getTopHeadlineNews(country)

    suspend fun getEverythingFromApi(searchQuery: String, pageNumber: Int) =
        apiServices.getEverythingNews(searchQuery, pageNumber)
}