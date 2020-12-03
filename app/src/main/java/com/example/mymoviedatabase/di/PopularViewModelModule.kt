package com.example.mymoviedatabase.di

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mymoviedatabase.model.ResultsItem
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.viewModelScope

class PopularViewModelModule @ViewModelInject constructor(private val popularRepositoryModule: PopularRepositoryModule) : ViewModel(){

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<ResultsItem>>? = null




    fun searchRepo(queryString: String): Flow<PagingData<ResultsItem>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<ResultsItem>> = popularRepositoryModule.getSearchResultStream()
            .cachedIn(viewModelScope)

        currentSearchResult = newResult
        return newResult
    }






//    val popular = popularRepositoryModule.getPopularMovie().cachedIn(viewModelScope)

    }




