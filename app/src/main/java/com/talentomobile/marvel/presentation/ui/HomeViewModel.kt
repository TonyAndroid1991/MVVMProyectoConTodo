package com.talentomobile.marvel.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talentomobile.marvel.data.models.AllCharacters
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase) : ViewModel() {

    private val _getAllCharactersLiveData: MutableLiveData<Resource<AllCharacters>> = MutableLiveData()
    val getAllCharactersLiveData = _getAllCharactersLiveData

    fun getAllCharacters(limit: Int) = viewModelScope.launch {
        _getAllCharactersLiveData.postValue(Resource.Loading())
        _getAllCharactersLiveData.postValue(getAllCharactersUseCase.getAllCharacters(limit))
    }
}