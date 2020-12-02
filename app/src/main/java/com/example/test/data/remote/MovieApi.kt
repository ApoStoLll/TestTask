package com.example.test.data.remote

import com.example.test.data.models.MovieModel
import com.example.test.data.remote.entities.MovieEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MovieApi{

    @GET("/3/trending/movie/day?api_key=689508dca17311272f9c69faf26248ac")
    @Headers("Content-Type: application/json")
    suspend fun getMovies() : MovieEntity
}