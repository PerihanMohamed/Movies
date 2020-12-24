package com.example.mymoviedatabase.paging

import androidx.paging.PagingSource
import com.example.mymoviedatabase.data.ApiService
import com.example.mymoviedatabase.di.NetworkModule.getClient

import com.example.mymoviedatabase.model.Movie
import com.example.mymoviedatabase.model.PopularResponse
import com.example.mymoviedatabase.utils.Category

import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1
private const val FIRST_PAGE = 1

class PopularPagingSource(
    private val service: ApiService,

    private val category: Category
    ) : PagingSource<Int, Movie>() {





    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val nextPageNumber = params.key ?:GITHUB_STARTING_PAGE_INDEX
            val response = getClient()

            when (category){
                Category.TOPRATED -> response.getTopRated(nextPageNumber)
                Category.UPCOMING -> response.geUpcomingMovies(nextPageNumber)
                else -> response.getPopular(nextPageNumber)

            }

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



