package com.example.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.test.data.IRepository
import com.example.test.data.Repository
import com.example.test.data.datasource.MoviesDataSource
import com.example.test.data.models.MovieModel
import com.example.test.data.remote.MovieApi
import com.example.test.data.utills.mapMovieEntitiesToMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(private val repository: IRepository) : ViewModel(){

    fun getDataSource(): LiveData<PagedList<MovieModel>> {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()

        val dataSourceFactory = object : DataSource.Factory<Int, MovieModel>(){
            override fun create(): DataSource<Int, MovieModel> {
                return MoviesDataSource(repository, viewModelScope)
            }
        }

        return LivePagedListBuilder(dataSourceFactory, config)
            .build()
    }

    fun getSearchMovie(query : String, callback : (List<MovieModel>) -> (Unit)){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchMovie(query = query)
            withContext(Dispatchers.Main) { callback.invoke(mapMovieEntitiesToMode(response)) }
        }
    }
}