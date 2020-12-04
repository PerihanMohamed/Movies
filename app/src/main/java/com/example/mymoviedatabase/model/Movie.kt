package com.example.mymoviedatabase.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Movie(







    @field:SerializedName("title")
    val title: String ,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,



    @field:SerializedName("id")
    val id: Int



)