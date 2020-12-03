package com.example.mymoviedatabase.di

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.Api_key
import com.example.mymoviedatabase.data.ApiService
import com.example.mymoviedatabase.model.ResultsItem
import com.example.mymoviedatabase.paging.PopularPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PopularRepositoryModule @Inject constructor (private  val apiService: ApiService){

    fun getSearchResultStream(): Flow<PagingData<ResultsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularPagingSource(apiService) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }



}