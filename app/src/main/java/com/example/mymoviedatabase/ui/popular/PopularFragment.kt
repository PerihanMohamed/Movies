package com.example.mymoviedatabase.ui.popular

import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.adapter.PopularAdapter

import com.example.mymoviedatabase.databinding.FragmentPopularBinding
import com.example.mymoviedatabase.di.PopularViewModelModule
import com.example.mymoviedatabase.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
 class PopularFragment : Fragment(R.layout.fragment_popular) ,PopularAdapter.OnItemClickListener {

    var _binding :FragmentPopularBinding ? = null
    private val binding get() =  _binding!!

    val pAdapter = PopularAdapter(this)
    private val viewModel by viewModels<PopularViewModelModule>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPopularBinding.bind(view)





        lifecycleScope.launch {
            viewModel.Movies.collectLatest { pagedData ->
                pAdapter.submitData(pagedData)
            }


    }

        val manager = GridLayoutManager(activity, 3 , GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = pAdapter


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onItemClick(movie: Movie) {
        val action = PopularFragmentDirections.actionPopularFragmentToDetailFragment(movie)
        findNavController().navigate(action)

    }

}


