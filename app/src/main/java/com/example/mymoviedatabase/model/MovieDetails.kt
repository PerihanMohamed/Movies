package com.example.mymoviedatabase.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(
    val budget: Int,
    val id: Int,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Double
) : Parcelable


//data class MovieDetail(
//    val id: Int,
//    val title: String,
//    val overview: String,
//    val poster_path: String,
//    val genres: List<GenreSingle>
//)
//
//data class GenreSingle(val id: Int, val name: String)