package com.example.mymoviedatabase.ui.popular

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Api_key

import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.adapter.PopularAdapter


import com.example.mymoviedatabase.di.PopularViewModelModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_popular.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PopularActivity : AppCompatActivity() {

    lateinit var viewModel:PopularViewModelModule
    val padapter = PopularAdapter()
//
//    private lateinit var binding:ActivityPopularBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular)

//        binding = ActivityPopularBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PopularViewModelModule::class.java)






        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = padapter.getItemViewType(position)
                if (viewType == padapter.itemCount) return  1    // Movie_VIEW_TYPE will occupy 1 out of 3 span
                else return 3                                              // NETWORK_VIEW_TYPE will occupy all 3 span
            }
        }


        rv_movie_list.layoutManager = gridLayoutManager
        rv_movie_list.setHasFixedSize(true)
        rv_movie_list.adapter = padapter











        lifecycleScope.launch {
            viewModel.searchRepo(Api_key).collectLatest {
                padapter.submitData(it)
            }


        }
    }



}