package com.example.test.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.test.data.datasource.MoviesDataSource
import com.example.test.data.models.MovieModel
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

    fun getDataSource(): LiveData<PagedList<MovieModel>> {
        //return MoviesDataSource(movieApi, CoroutineScope(coroutineContext))
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()

        val dataSourceFactory = object : DataSource.Factory<Int, MovieModel>(){
            override fun create(): DataSource<Int, MovieModel> {
                return MoviesDataSource(movieApi, CoroutineScope(coroutineContext))
            }
        }

        val pagedList = LivePagedListBuilder<Int, MovieModel>(dataSourceFactory, config)
            .build()

        return pagedList
    }
}