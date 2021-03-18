package com.example.test.data

import com.example.test.data.remote.entities.MovieDetailEntity
import com.example.test.data.remote.entities.MovieEntity

interface IRepository {
    suspend fun searchMovie(query : String) : MovieEntity
    suspend fun getMovies() : MovieEntity
    suspend fun getMovieDetail(id : Int) : MovieDetailEntity
}