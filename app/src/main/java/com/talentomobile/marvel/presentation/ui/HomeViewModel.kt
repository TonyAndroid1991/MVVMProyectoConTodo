package com.talentomobile.marvel.presentation.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase
import com.talentomobile.marvel.domain.usecases.GetCharactersFromDataBaseUseCase

import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharactersFromDataBaseUseCase: GetCharactersFromDataBaseUseCase,
    private val context: Application
) : ViewModel() {

    private val _getAllCharactersResponseLiveData: MutableLiveData<Resource<List<MarvelCharacter>>> =
        MutableLiveData()
    val getAllCharactersLiveData: LiveData<Resource<List<MarvelCharacter>>> =
        _getAllCharactersResponseLiveData

    fun getAllCharacters() = viewModelScope.launch {
        _getAllCharactersResponseLiveData.postValue(Resource.Loading())
        val charactersList = getCharactersFromDataBaseUseCase.getAllCharactersFromDataBase()

        if (isOnline(context) && charactersList.isEmpty()) {
            _getAllCharactersResponseLiveData.postValue(getAllCharactersUseCase.getAllCharacters())
        } else if (!isOnline(context) && charactersList.isNotEmpty() ||
            isOnline(context) && charactersList.isNotEmpty()
        ) {
            _getAllCharactersResponseLiveData.postValue(
                Resource.Success(
                    getCharactersFromDataBaseUseCase.getAllCharactersFromDataBase()
                )
            )
        } else {
            _getAllCharactersResponseLiveData.postValue(Resource.Error())
        }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            }
        }
        return false
    }
}