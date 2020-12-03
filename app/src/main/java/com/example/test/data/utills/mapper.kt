package com.example.test.data.utills

import com.example.test.data.models.MovieDetailModel
import com.example.test.data.models.MovieModel
import com.example.test.data.remote.entities.MovieDetailEntity
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
        id = movie.id,
        image = movie.poster_path,
        name = movie.title,
        descr = movie.overview
    )
}

fun mapMovieEntityToDetailModel(movie: MovieDetailEntity) : MovieDetailModel{
    var directors = ""
    for(company in movie.production_companies)
        directors += " " + company.name
    var genre = ""
    for(genr in movie.genres)
        genre += " " + genr.name
    return MovieDetailModel(
        poster = movie.poster_path,
        description = movie.overview,
        release = movie.release_date,
        directors = directors,
        genres = genre,
        cast = movie.status,
    )
}