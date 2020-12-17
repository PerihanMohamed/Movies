package com.example.mymoviedatabase.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.POSTER_BASE_URL
import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.databinding.FragmentDetailBinding
import com.example.mymoviedatabase.di.DetailViewModelModule

import com.example.mymoviedatabase.model.MovieDetails
import com.example.mymoviedatabase.model.Resource
import com.example.mymoviedatabase.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

import retrofit2.http.Tag
import java.text.NumberFormat
import java.util.*


@AndroidEntryPoint
class DetailFragment:Fragment(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>()
//    val safeArgs: DetailFragmentArgs by navArgs()



    var _binding : FragmentDetailBinding? = null
//    private var binding =  _binding!!

    private val viewModel by viewModels<DetailViewModelModule>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailBinding.bind(view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = args.movie.id

           viewModel.loadMovie(id).observe(viewLifecycleOwner , Observer {
                   when (it.status) {
                       Status.LOADING -> {
                           Log.d(tag , "my loading" )
                       }
                       Status.SUCCESS -> {
                           val movie = it.data
                           movie?.let {
                                 val movie = it.body()

                               if (movie != null) {
                                   updateUI(movie)
                               }
                           }
                       }
                       Status.ERROR -> {
                          Log.e(tag , "error")
                       }
               }
           })


    }

    private fun updateUI(movie:MovieDetails) {
        movie_title.text = movie.title
        movie_tagline.text = movie.tagline
        movie_release_date.text = movie.releaseDate
        movie_rating.text = movie.rating.toString()
        movie_runtime.text = movie.runtime.toString()
        movie_overview.text = movie.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(movie.budget)
        movie_revenue.text = formatCurrency.format(movie.revenue)

        val moviePosterURL = POSTER_BASE_URL + movie.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}