package com.example.mymoviedatabase.paging

import androidx.paging.PagingSource
import com.example.mymoviedatabase.data.ApiService

import com.example.mymoviedatabase.model.Movie
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1
private const val FIRST_PAGE = 1


class PopularPagingSource(
    private val service: ApiService,

) : PagingSource<Int, Movie>() {



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

//        return try {
//            val position = params.key ?:0
//            val response = service.getPopular(position)
//            val photos = response.results
//
//            LoadResult.Page(
//               data = photos,
////                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
////                nextKey = if (photos.isEmpty()) null else position + 1
//                prevKey = if (position > 0) position - 1 else null,
//                nextKey = if (position < response.totalPages) position + 1 else null
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }

        return try {
            val nextPageNumber = params.key ?:GITHUB_STARTING_PAGE_INDEX
            val response = service.getPopular(nextPageNumber)
            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}



