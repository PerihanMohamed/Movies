package com.example.mymoviedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.POSTER_BASE_URL
import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.databinding.ItemPopularBinding

import com.example.mymoviedatabase.model.Movie
import com.example.mymoviedatabase.utils.loadImage


class PopularAdapter : PagingDataAdapter<Movie, PopularAdapter.PopularViewHolder>(PhotoComparator) {

  inner   class PopularViewHolder (private val binding: ItemPopularBinding )
        : RecyclerView.ViewHolder(binding.root){



        fun bindPost( movie : Movie)= with(binding){
            val moviePosterURL = POSTER_BASE_URL + movie.posterPath
            moviePoster.loadImage(moviePosterURL)
            movieTitle.text = movie.title

        }
    }


    companion object PhotoComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder  .bindPost(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder (ItemPopularBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }
}