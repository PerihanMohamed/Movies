package com.example.mymoviedatabase.data

import com.example.mymoviedatabase.model.PopularResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {




    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page")page : Int
    ) : PopularResponse
}
