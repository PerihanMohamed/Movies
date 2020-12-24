package com.example.mymoviedatabase.di

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.cachedIn
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.mymoviedatabase.data.ApiService
import com.example.mymoviedatabase.paging.PopularPagingSource
import com.example.mymoviedatabase.utils.Category

class PopularViewModelModule @ViewModelInject constructor(private val apiService: ApiService , private val category: Category) : ViewModel() {

    val Movies = Pager(PagingConfig(pageSize = 10)) {
        PopularPagingSource(apiService , category)
    }.flow.cachedIn(viewModelScope)

}


