package com.clean.example.di

import com.clean.example.presentation.main.MainViewModel
import com.clean.example.presentation.main.first.FirstViewModel
import com.clean.example.presentation.main.second.SecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { MainViewModel(preferencesRepository = get()) }

    viewModel { FirstViewModel() }
    viewModel { SecondViewModel() }
}