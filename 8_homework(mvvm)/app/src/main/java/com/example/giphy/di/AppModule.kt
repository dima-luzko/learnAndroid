package com.example.giphy.di

import com.example.giphy.data.remote.RemoteDataSource
import com.example.giphy.data.repository.GifRepository
import com.example.giphy.data.repository.GifRepositoryImpl
import com.example.giphy.ui.GifViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val gifModules = module {
    single { RemoteDataSource }
    single<GifRepository> { GifRepositoryImpl(get()) }
    viewModel { GifViewModel(get()) }
}