package com.example.test.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test.data.models.MovieModel

class MoviesAdapter : PagedListAdapter<MovieModel, MoviesAdapter.MoviesViewHolder>(DiffUtillCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bindMovie(it) }
    }

    class MoviesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindMovie(movieModel: MovieModel){

        }
    }
}