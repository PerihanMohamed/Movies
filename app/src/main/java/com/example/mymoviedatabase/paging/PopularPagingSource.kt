package com.example.mymoviedatabase.paging

import androidx.paging.PagingSource
import com.example.Api_key
import com.example.mymoviedatabase.data.ApiService

import com.example.mymoviedatabase.model.PopularResponse
import com.example.mymoviedatabase.model.ResultsItem
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1
private const val FIRST_PAGE = 1


class PopularPagingSource(
    private val service: ApiService,

) : PagingSource<Int, ResultsItem>() {



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX


        return try {

            val response = service.getPopular(FIRST_PAGE  )
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}



