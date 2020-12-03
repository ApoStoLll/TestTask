package com.example.test.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.App
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.adapter.MoviesAdapter
import com.example.test.data.datasource.MoviesDataSource
import com.example.test.data.models.MovieModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var presenter: MovieListPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    @SuppressLint("WrongThread")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieApi = ((activity as MainActivity).application as App).movieApi
        presenter = MovieListPresenter(movieApi)
        presenter.getMovies()
        val adapter = MoviesAdapter(activity as MainActivity)
        presenter.getDataSource().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        movies_recycler.layoutManager = LinearLayoutManager(activity as MainActivity)
        movies_recycler.adapter = adapter
    }


}