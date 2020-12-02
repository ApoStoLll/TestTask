package com.example.test.ui.main

import android.util.Log
import com.example.test.data.remote.MovieApi
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MovieListPresenter(val movieApi: MovieApi) : CoroutineScope{

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun getMovies(){
        launch {
            withContext(Dispatchers.IO){
                //Log.e("MOvie" , movieApi.getMovies(2).toString())
            }
        }
    }
}