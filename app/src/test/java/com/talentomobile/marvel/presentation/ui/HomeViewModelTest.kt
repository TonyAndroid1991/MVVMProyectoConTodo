package com.talentomobile.marvel.presentation.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase
import com.talentomobile.marvel.domain.usecases.GetCharacterFromNameUseCase
import com.talentomobile.marvel.domain.usecases.GetCharactersFromDataBaseUseCase
import com.talentomobile.marvel.presentation.utils.TestUtils
import com.talentomobile.marvel.presentation.utils.getValueForTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    lateinit var sut: HomeViewModel
    lateinit var getAllCharactersUseCase: GetAllCharactersUseCase
    lateinit var getCharactersFromDataBaseUseCase: GetCharactersFromDataBaseUseCase
    lateinit var getCharacterFromNameUseCase: GetCharacterFromNameUseCase
    lateinit var context: Application
    lateinit var connectivityManager: ConnectivityManager
    lateinit var networkCapabilities: NetworkCapabilities
    val dispatcher = TestCoroutineDispatcher()
    val expected = TestUtils().listOfMarvelCharacter

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        getAllCharactersUseCase = mock()
        getCharactersFromDataBaseUseCase = mock()
        getCharacterFromNameUseCase = mock()
        context = mock()
        sut = HomeViewModel(getAllCharactersUseCase, getCharactersFromDataBaseUseCase, getCharacterFromNameUseCase, context)
        Dispatchers.setMain(dispatcher)
        connectivityManager = mock()
        networkCapabilities = mock()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllCharactersInViewModelIsCalled() = runTest {
        whenever(getCharactersFromDataBaseUseCase.getAllCharactersFromDataBase()).thenReturn(listOf())
        whenever( context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        whenever(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)).thenReturn(networkCapabilities)
        whenever(sut.isOnline(context)).thenReturn(true)
        sut.getAllCharacters()
        verify(getCharactersFromDataBaseUseCase, times(1)).getAllCharactersFromDataBase()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun liveDataFetchInfoFromGetCharactersUseCase() = runTest {
        whenever(getCharactersFromDataBaseUseCase.getAllCharactersFromDataBase()).thenReturn(listOf())
        whenever(getAllCharactersUseCase.getAllCharacters()).thenReturn(Resource.Success(TestUtils().listOfMarvelCharacter))
        whenever( context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        whenever(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)).thenReturn(networkCapabilities)
        whenever(sut.isOnline(context)).thenReturn(true)
        sut.getAllCharacters()
        val result = sut.getAllCharactersLiveData.getValueForTest()?.data
        assertEquals(expected[0].id,  result?.get(0)?.id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun liveDataFetchInfoFromDatabaseWhenOnlineAndListOnDataBase() = runTest {
        whenever(getCharactersFromDataBaseUseCase.getAllCharactersFromDataBase()).thenReturn(TestUtils().listOfMarvelCharacter)
        whenever(getAllCharactersUseCase.getAllCharacters()).thenReturn(Resource.Success(TestUtils().listOfMarvelCharacter))
        whenever( context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        whenever(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)).thenReturn(networkCapabilities)
        whenever(sut.isOnline(context)).thenReturn(true)
        sut.getAllCharacters()
        val result = sut.getAllCharactersLiveData.getValueForTest()?.data
        assertEquals(expected[0].id,  result?.get(0)?.id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun liveDataFetchInfoFromApiWhenOnlineAndEmptyList() = runTest {
        whenever(getCharactersFromDataBaseUseCase.getAllCharactersFromDataBase()).thenReturn(listOf())
        whenever(getAllCharactersUseCase.getAllCharacters()).thenReturn(Resource.Success(TestUtils().listOfMarvelCharacter))
        whenever( context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        whenever(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)).thenReturn(networkCapabilities)
        whenever(sut.isOnline(context)).thenReturn(true)
        sut.getAllCharacters()
        val result = sut.getAllCharactersLiveData.getValueForTest()?.data
        assertEquals(expected[0].id,  result?.get(0)?.id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun errorWhenNoInternetConnectionAndEmptyList() = runTest {
        whenever(getCharactersFromDataBaseUseCase.getAllCharactersFromDataBase()).thenReturn(listOf())
        whenever(getAllCharactersUseCase.getAllCharacters()).thenReturn(Resource.Error())
        whenever( context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        whenever(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)).thenReturn(networkCapabilities)
        whenever(sut.isOnline(context)).thenReturn(false)
        sut.getAllCharacters()
        val result = sut.getAllCharactersLiveData.getValueForTest()
        assertTrue(result is Resource.Error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getCharacterFromNameExistRetrievesLivedata() = runTest {
        whenever(getCharacterFromNameUseCase.getCharacterFromName(any())).thenReturn(Resource.Success(TestUtils().marvelCharacter))
        sut.getCharacterFromName("string")
        val result = sut.getCharacterFromNameLiveData.getValueForTest()?.peekContent()?.data
        assertEquals(result?.id, 1)
    }


}