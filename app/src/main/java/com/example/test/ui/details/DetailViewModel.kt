package com.example.test.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.IRepository
import com.example.test.data.models.MovieDetailModel
import com.example.test.data.remote.MovieApi
import com.example.test.data.utills.mapMovieEntityToDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val repository: IRepository) : ViewModel(){
    fun getMovie(id : Int, callback : (MovieDetailModel) -> (Unit)){
        viewModelScope.launch(Dispatchers.IO) {
            val data = mapMovieEntityToDetailModel(repository.getMovieDetail(id))
            withContext(Dispatchers.Main) { callback.invoke(data) }
        }
    }
}