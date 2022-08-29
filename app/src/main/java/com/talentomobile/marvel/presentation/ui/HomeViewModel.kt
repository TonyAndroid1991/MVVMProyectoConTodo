package com.talentomobile.marvel.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase) : ViewModel() {

    private val _getAllCharactersResponseLiveData: MutableLiveData<Resource<List<MarvelCharacter>>> = MutableLiveData()
    val getAllCharactersLiveData: LiveData<Resource<List<MarvelCharacter>>> = _getAllCharactersResponseLiveData

    fun getAllCharacters(limit: Int) = viewModelScope.launch {
        _getAllCharactersResponseLiveData.postValue(Resource.Loading())
        _getAllCharactersResponseLiveData.postValue(getAllCharactersUseCase.getAllCharacters(limit))
    }
}