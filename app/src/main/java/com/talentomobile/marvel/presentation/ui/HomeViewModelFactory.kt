package com.talentomobile.marvel.presentation.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase
import com.talentomobile.marvel.domain.usecases.GetCharacterFromNameUseCase
import com.talentomobile.marvel.domain.usecases.GetCharactersFromDataBaseUseCase

class HomeViewModelFactory(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharactersFromDataBaseUseCase: GetCharactersFromDataBaseUseCase,
    private val getCharacterFromNameUseCase: GetCharacterFromNameUseCase,
    private val context: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getAllCharactersUseCase, getCharactersFromDataBaseUseCase, getCharacterFromNameUseCase, context) as T
    }
}