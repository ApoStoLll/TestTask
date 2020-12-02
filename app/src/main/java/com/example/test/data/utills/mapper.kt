package com.example.test.data.utills

import com.example.test.data.models.MovieModel
import com.example.test.data.remote.entities.MovieEntity
import com.example.test.data.remote.entities.Results


fun mapMovieEntitiesToMode(movieEntity: MovieEntity) : List<MovieModel>{
    val movieModels = mutableListOf<MovieModel>()
    for(movie in movieEntity.results){
        movieModels.add(mapMovieEntityToModel(movie = movie))
    }
    return movieModels
}

fun mapMovieEntityToModel(movie: Results) : MovieModel {
    return MovieModel(
        image = movie.poster_path,
        name = movie.title,
        descr = movie.overview
    )
}