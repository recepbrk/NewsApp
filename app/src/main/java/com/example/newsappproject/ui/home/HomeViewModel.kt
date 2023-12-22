package com.example.newsappproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappproject.data.model.ResponseTopHeadLine
import com.example.newsappproject.data.repository.NewsRepository
import com.example.newsappproject.util.resource.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _newsList: MutableLiveData<DataStatus<ResponseTopHeadLine>> = MutableLiveData()
    val newsList: LiveData<DataStatus<ResponseTopHeadLine>>
        get() = _newsList

    fun getTopHeadlineNews(country: String) = viewModelScope.launch {
        _newsList.value = DataStatus.loading()
        val response = repository.getTopHeadlineNews(country)
        _newsList.value = handleTopHeadlineNewsResponse(response)
    }

    private fun handleTopHeadlineNewsResponse(response: Response<ResponseTopHeadLine>): DataStatus<ResponseTopHeadLine> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return DataStatus.success(resultResponse)
            }
        }
        return DataStatus.error(response.message())
    }

}
