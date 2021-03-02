package com.example.test.di

import com.example.test.ui.details.DetailViewModel
import com.example.test.ui.main.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MovieListViewModel(get())
    }
    viewModel {
        DetailViewModel(get())
    }
}