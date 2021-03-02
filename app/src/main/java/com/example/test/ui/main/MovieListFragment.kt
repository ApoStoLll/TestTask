package com.example.test.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.adapter.MoviesAdapter
import com.example.test.adapter.SearchAdapter
import com.example.test.databinding.FragmentMovieListBinding
import com.example.test.ui.details.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    // TODO: Rename and change types of parameters

    private val viewModel : MovieListViewModel by viewModel()
    private val binding by viewBinding(FragmentMovieListBinding::bind)


    @SuppressLint("WrongThread")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MoviesAdapter(requireActivity()){
            findNavController().navigate(R.id.detailFragment, DetailFragment.newInstance(it.id))
        }
        viewModel.getDataSource().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        val searchAdapter = SearchAdapter(requireActivity()){
            findNavController().navigate(R.id.detailFragment, DetailFragment.newInstance(it.id))
        }
        binding.searctBtn.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if(query != ""){
                viewModel.getSearchMovie(query){
                    searchAdapter.setData(it)
                    binding.moviesRecycler.adapter = searchAdapter
                }
            }
        }

        binding.moviesRecycler.layoutManager = LinearLayoutManager(activity as MainActivity)
        binding.moviesRecycler.adapter = adapter
    }

}