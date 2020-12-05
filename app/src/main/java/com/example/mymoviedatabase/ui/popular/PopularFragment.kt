package com.example.mymoviedatabase.ui.popular

import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.adapter.PopularAdapter

import com.example.mymoviedatabase.databinding.FragmentPopularBinding
import com.example.mymoviedatabase.di.PopularViewModelModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PopularFragment : Fragment(R.layout.fragment_popular) {

    var _binding :FragmentPopularBinding ? = null
    private val binding get() =  _binding!!

    val pAdapter = PopularAdapter()
    private val viewModel by viewModels<PopularViewModelModule>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPopularBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = pAdapter

        lifecycleScope.launch {
            viewModel.Movies.collectLatest { pagedData ->
                pAdapter.submitData(pagedData)
            }


    }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


