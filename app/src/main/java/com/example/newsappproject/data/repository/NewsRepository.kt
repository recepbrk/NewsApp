package com.example.newsappproject.data.repository

import com.example.newsappproject.data.model.ResponseTopHeadLine
import com.example.newsappproject.data.source.local.NewsDao
import com.example.newsappproject.data.source.remote.ApiServises
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiServices: ApiServises,
    private val database: NewsDao
) {

    suspend fun getTopHeadlineNews(country: String) = apiServices.getTopHeadlineNews(country)

    suspend fun getEverythingFromApi(searchQuery: String, pageNumber: Int) =
        apiServices.getEverythingNews(searchQuery, pageNumber)

    suspend fun addArticle(article: ResponseTopHeadLine.Article): Boolean {
        val articleTitles: List<String>
        withContext(Dispatchers.IO) {
            articleTitles = database.getArticleTitles()
        }
        return if (!articleTitles.contains(article.title)) {
            database.addArticle(article)
            true
        } else {
            false
        }
    }

    fun getFavArticles() = database.getFavArticles()

    suspend fun deleteArticle(article: ResponseTopHeadLine.Article) =
        database.deleteArticle(article)
}