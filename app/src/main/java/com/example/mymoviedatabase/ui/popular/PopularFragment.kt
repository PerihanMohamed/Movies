package com.example.mymoviedatabase.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedatabase.adapter.PopularAdapter
import com.example.mymoviedatabase.data.ApiService
import com.example.mymoviedatabase.databinding.FragmentPopularBinding
import com.example.mymoviedatabase.di.PopularViewModelModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PopularFragment : Fragment(){

    private lateinit var viewModel: PopularViewModelModule
    private lateinit var binding: FragmentPopularBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPopularBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val factory = PopularViewModelFctory(ApiService)
        viewModel = ViewModelProvider(this).get(PopularViewModelModule::class.java)

        val pAdapter = PopularAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.adapter = pAdapter

        lifecycleScope.launch {
            viewModel.Movies.collectLatest { pagedData ->
                pAdapter.submitData(pagedData)
            }
        }
    }


}