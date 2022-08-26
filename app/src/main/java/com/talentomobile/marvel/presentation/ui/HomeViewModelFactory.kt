package com.talentomobile.marvel.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase

class HomeViewModelFactory(private val getAllCharactersUseCase: GetAllCharactersUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getAllCharactersUseCase) as T
    }
}