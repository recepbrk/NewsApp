package com.example.newsappproject.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappproject.data.model.ResponseTopHeadLine
import com.example.newsappproject.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val newsRepo: NewsRepository) : ViewModel() {

    val isAddedFavorite: MutableLiveData<Boolean> = MutableLiveData()

    fun addFavoriteArticle(article: ResponseTopHeadLine.Article) = viewModelScope.launch {
        isAddedFavorite.value = newsRepo.addArticle(article)
    }
}
