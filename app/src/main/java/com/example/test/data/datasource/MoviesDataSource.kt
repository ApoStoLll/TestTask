package com.example.test.data.datasource

import androidx.paging.PageKeyedDataSource
import androidx.paging.PositionalDataSource
import com.example.test.data.IRepository
import com.example.test.data.Repository
import com.example.test.data.models.MovieModel
import com.example.test.data.remote.MovieApi
import com.example.test.data.utills.mapMovieEntitiesToMode
import kotlinx.coroutines.*

class MoviesDataSource(
        private val repository: IRepository,
        private val coroutineScope: CoroutineScope
) : PageKeyedDataSource<Int, MovieModel>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieModel>
    ) {
        coroutineScope.launch {
            val data = withContext(Dispatchers.IO) {
                mapMovieEntitiesToMode(repository.getMovies())
            }
            callback.onResult(data, null, 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
        var page : Int? = params.key + 1
        coroutineScope.launch {
            val data = withContext(Dispatchers.IO) {
                val response = repository.getMovies()
                if (page != null) {
                    if(page!! >= response.total_pages) page = null
                }
                mapMovieEntitiesToMode(response)
            }
            callback.onResult(data, page)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
        var page : Int? = params.key - 1
        coroutineScope.launch {
            val data = withContext(Dispatchers.IO) {
                val response = repository.getMovies()
                if (page != null) {
                    if(page!! <= 0) page = null
                }
                mapMovieEntitiesToMode(response)
            }
            callback.onResult(data, page)
        }
    }


}