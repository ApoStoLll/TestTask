package com.example.test.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.test.data.models.MovieModel

class DiffUtillCallback : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.name == newItem.name
    }
}