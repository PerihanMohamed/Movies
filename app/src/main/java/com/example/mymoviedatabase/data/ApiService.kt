package com.example.mymoviedatabase.data

import com.example.mymoviedatabase.model.MovieDetails
import com.example.mymoviedatabase.model.PopularResponse

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

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page")page: Int
    ): PopularResponse

    @GET("movie/upcoming")
    suspend fun  geUpcomingMovies(
        @Query("page")page: Int
    ):PopularResponse




    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetail(@Path ("movie_id") id : Int): Response<MovieDetails>
}
