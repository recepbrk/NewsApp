package com.example.newsappproject.ui.category.categorydetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappproject.data.model.ResponseTopHeadLine
import com.example.newsappproject.data.repository.ApiRepository
import com.example.newsappproject.util.resource.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(private val repository: ApiRepository) :
    ViewModel() {


    val searchNews: MutableLiveData<DataStatus<ResponseTopHeadLine>> = MutableLiveData()
    val pageNumber = 1

    fun getNewsEverything(searchquery: String) = viewModelScope.launch {
        searchNews.value = DataStatus.loading()
        val getEverythingResponse = repository.getEverythingFromApi(searchquery, pageNumber)
        searchNews.postValue(handleSearchNews(getEverythingResponse))
    }

    private fun handleSearchNews(response: Response<ResponseTopHeadLine>): DataStatus<ResponseTopHeadLine> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return DataStatus.success(resultResponse)
            }
        }
        return DataStatus.error(response.message())
    }

}
