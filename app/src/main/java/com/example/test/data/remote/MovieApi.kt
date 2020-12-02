package com.example.test.data.remote

import com.example.test.data.models.MovieModel
import com.example.test.data.remote.entities.MovieEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieApi{
    @GET("/trending/movie/day?api_key=689508dca17311272f9c69faf26248ac")
    fun getMovies() : Deferred<List<MovieEntity>>
}