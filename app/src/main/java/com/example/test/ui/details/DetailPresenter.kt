package com.example.test.ui.details

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.test.data.datasource.MoviesDataSource
import com.example.test.data.models.MovieDetailModel
import com.example.test.data.models.MovieModel
import com.example.test.data.remote.MovieApi
import com.example.test.data.utills.mapMovieEntitiesToMode
import com.example.test.data.utills.mapMovieEntityToDetailModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class DetailPresenter(val movieApi: MovieApi) : CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun getMovie(id : Int, callback : (MovieDetailModel) -> (Unit)){
        launch(Dispatchers.IO) {
            val data = mapMovieEntityToDetailModel(movieApi.getMovieDetails(id))
            withContext(Dispatchers.Main) { callback.invoke(data) }
        }
    }

    fun onClear(){
        job.cancel()
    }

}

