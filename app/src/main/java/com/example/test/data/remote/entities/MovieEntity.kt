package com.example.test.data.remote.entities

data class MovieEntity(
    val page : Int,
    val results : List<Results>,
    val total_pages : Int,
    val total_results : Int
)

data class Results(
    val poster_path : String?,
    val adult : Boolean,
    val overview : String,
    val releaseData : String,
    val genre_ids : List<Int>,
    val id : Int,
    val original_title : String,
    val original_language : String,
    val title : String,
    val backdrop_path : String?,
    val popularity : Double,
    val vote_count : Int,
    val video : Boolean,
    val vote_average : Double
)