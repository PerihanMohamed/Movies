package com.example.mymoviedatabase.model

import com.google.gson.annotations.SerializedName


data class PopularResponse(

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<Movie>,

	@field:SerializedName("total_results")
	val totalResults: Int
)


