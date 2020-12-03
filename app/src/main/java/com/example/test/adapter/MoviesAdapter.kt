package com.example.test.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.models.MovieModel
import com.squareup.picasso.Picasso

class MoviesAdapter(val context : Context) : PagedListAdapter<MovieModel, MoviesAdapter.MoviesViewHolder>(DiffUtillCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false), context)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bindMovie(it) }
    }

    class MoviesViewHolder(itemView : View, val context: Context) : RecyclerView.ViewHolder(itemView){
        val titleText = itemView.findViewById<TextView>(R.id.text_movie_name)
        val descriptionText = itemView.findViewById<TextView>(R.id.text_description)
        val posterImage = itemView.findViewById<ImageView>(R.id.image_poster)

        fun bindMovie(movieModel: MovieModel){
            titleText.text = movieModel.name
            descriptionText.text = movieModel.descr
            movieModel.image?.let { Log.e("Holder", it) }
            Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500" + movieModel.image)
                .into(posterImage)
        }
    }
}