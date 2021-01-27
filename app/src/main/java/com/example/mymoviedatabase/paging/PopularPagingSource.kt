package com.example.mymoviedatabase.paging

import androidx.paging.PagingSource
import com.example.mymoviedatabase.data.ApiService
import com.example.mymoviedatabase.di.PopularViewModelModule

import com.example.mymoviedatabase.model.Movie

private const val GITHUB_STARTING_PAGE_INDEX = 1
private const val FIRST_PAGE = 1

class PopularPagingSource(
    private val service: ApiService,

    ) : PagingSource<Int, Movie>() {





    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val nextPageNumber = params.key ?:GITHUB_STARTING_PAGE_INDEX




            val responses = service.getPopular(nextPageNumber)
            LoadResult.Page(
                data = responses.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < responses.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}



