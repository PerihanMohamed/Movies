package com.example.mymoviedatabase.di

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mymoviedatabase.data.ApiService
import com.example.mymoviedatabase.model.Resource
import kotlinx.coroutines.Dispatchers

class DetailViewModelModule @ViewModelInject constructor( val apiService: ApiService) :ViewModel() {





    fun loadMovie(id: Int)= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data=apiService.fetchMovieDetail(id)))
        }catch (exception: Exception){
            emit(Resource.error(data=null,message = exception.message?:"Error occured"))
        }
    }


}

           


