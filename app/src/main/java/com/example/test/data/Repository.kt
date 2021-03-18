package com.example.test.data

import com.example.test.data.remote.MovieApi
import com.example.test.data.remote.entities.MovieDetailEntity
import com.example.test.data.remote.entities.MovieEntity

class Repository(private val movieApi: MovieApi) : IRepository {
    override suspend fun searchMovie(query: String): MovieEntity {
        return movieApi.searchMovie(query = query)
    }

    override suspend fun getMovies(): MovieEntity {
        return movieApi.getMovies()
    }

    override suspend fun getMovieDetail(id : Int): MovieDetailEntity {
        return movieApi.getMovieDetails(id)
    }
}