package com.clean.example.presentation.main

import com.clean.example.data.repositories.PreferencesRepository
import com.clean.example.presentation.base.BaseViewModel

class MainViewModel(
    private val preferencesRepository: PreferencesRepository
): BaseViewModel<MainViewEvent>() {
}