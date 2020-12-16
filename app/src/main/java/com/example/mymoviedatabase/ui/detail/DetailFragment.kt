package com.example.mymoviedatabase.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.databinding.FragmentDetailBinding
import com.example.mymoviedatabase.di.DetailViewModelModule
import com.example.mymoviedatabase.model.MovieDetails
import com.example.mymoviedatabase.model.Resource
import com.example.mymoviedatabase.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

import retrofit2.http.Tag


@AndroidEntryPoint
class DetailFragment:Fragment(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>()
//    val safeArgs: DetailFragmentArgs by navArgs()



    var _binding : FragmentDetailBinding? = null
    private val binding get() =  _binding!!

    private val viewModel by viewModels<DetailViewModelModule>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailBinding.bind(view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = args.id

           viewModel.loadMovie(id).observe(viewLifecycleOwner , Observer {networkResource ->
                   when (networkResource.status) {
                       Status.LOADING -> {
                           Log.d(tag , "my loading" )
                       }
                       Status.SUCCESS -> {
                           val movie = networkResource.data
                           movie?.let {

                              movie_title.text





                           }
                       }
                       Status.ERROR -> {
                          Log.e(tag , "error")
                       }


               }
           })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private  fun retriveMovie(movieDetails:  MovieDetails){
//        movie_title.text = movieDetails.title
//
//
//    }
}