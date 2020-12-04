package com.example.mymoviedatabase.ui.popular
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.mymoviedatabase.data.ApiService
//import com.example.mymoviedatabase.di.PopularViewModelModule
//
//@Suppress("UNCHECKED_CAST")
//
//class PopularViewModelFctory(private val apiService: ApiService.Companion) : ViewModelProvider.NewInstanceFactory() {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return PopularViewModelModule(apiService) as T
//    }
//}