package com.example.test.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.models.MovieModel
import com.squareup.picasso.Picasso

class SearchAdapter(val context: Context, val callback : (MovieModel) -> (Unit)) : RecyclerView.Adapter<SearchAdapter.MyMovieViewHolder>() {

    private val data = mutableListOf<MovieModel>()

    fun setData(newReports : List<MovieModel>){
        data.clear()
        data.addAll(newReports)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.MyMovieViewHolder {
        return MyMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false))
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: SearchAdapter.MyMovieViewHolder, position: Int) {
        holder.bind(model = data[position])
    }

    inner class MyMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleText = itemView.findViewById<TextView>(R.id.text_movie_name)
        val descriptionText = itemView.findViewById<TextView>(R.id.text_description)
        val posterImage = itemView.findViewById<ImageView>(R.id.image_poster)
        val root = itemView.findViewById<ConstraintLayout>(R.id.root)

        fun bind(model : MovieModel){
            titleText.text = model.name
            descriptionText.text = model.descr
            model.image?.let { Log.e("Holder", it) }
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + model.image)
                    .into(posterImage)
            root.setOnClickListener {
                callback.invoke(model)
            }
        }
    }

}