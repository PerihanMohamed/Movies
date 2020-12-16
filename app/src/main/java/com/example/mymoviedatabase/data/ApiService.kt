package com.example.mymoviedatabase.data

import com.example.mymoviedatabase.model.MovieDetails
import com.example.mymoviedatabase.model.PopularResponse
import com.example.mymoviedatabase.model.Resource
import com.example.mymoviedatabase.model.ServiceResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {




    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page")page : Int
    ) : PopularResponse


    @GET("movie/id")
    suspend fun getMovieDetail(@Path ("id") id : Int) : Response<MovieDetails>
}
