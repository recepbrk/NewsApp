package com.example.newsappproject.ui.save

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappproject.data.model.ResponseTopHeadLine
import com.example.newsappproject.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    fun addArticle(article: ResponseTopHeadLine.Article) = viewModelScope.launch {
        newsRepository.addArticle(article)
    }

    fun getFavArticles() = newsRepository.getFavArticles()

    fun deleteArticle(article: ResponseTopHeadLine.Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}