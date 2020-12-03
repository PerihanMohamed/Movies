package com.example.mymoviedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.POSTER_BASE_URL
import com.example.mymoviedatabase.R

import com.example.mymoviedatabase.model.ResultsItem
import kotlinx.android.synthetic.main.popular_item.view.*

class PopularAdapter : PagingDataAdapter<ResultsItem, PopularAdapter.PopularViewHolder>(PhotoComparator) {

     class PopularViewHolder (view :View)
        : RecyclerView.ViewHolder(view){

//        val binding = PopularItemBinding.bind(view)

        fun bindPost( movie : ResultsItem){

            itemView.cv_movie_title.text = movie.title


            val moviePosterURL = POSTER_BASE_URL + movie.posterPath
            Glide.with(itemView.context)
                .load(moviePosterURL)
                .into(itemView.cv_iv_movie_poster);

        }
    }


    companion object PhotoComparator : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResultsItem,
            newItem: ResultsItem
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
        return PopularViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.popular_item, parent, false))
    }
}