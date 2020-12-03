package com.example.test.data.remote

import com.example.test.data.models.MovieModel
import com.example.test.data.remote.entities.MovieDetailEntity
import com.example.test.data.remote.entities.MovieEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface MovieApi{

    @GET("/3/trending/movie/day?api_key=689508dca17311272f9c69faf26248ac")
    @Headers("Content-Type: application/json")
    suspend fun getMovies(@Query("page") page : Int = 1) : MovieEntity

    @GET("/3/search/movie?api_key=689508dca17311272f9c69faf26248ac")
    @Headers("Content-Type: application/json")
    suspend fun searchMovie(@Query("query") query : String, @Query("page") page : Int = 1) : MovieEntity

    @GET("/3/movie/{movie_id}?api_key=689508dca17311272f9c69faf26248ac")
    @Headers("Content-Type: application/json")
    suspend fun getMovieDetails(@Path("movie_id") movieId : Int) : MovieDetailEntity

}