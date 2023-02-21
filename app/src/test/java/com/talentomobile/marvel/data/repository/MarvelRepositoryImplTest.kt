package com.talentomobile.marvel.data.repository

import com.talentomobile.marvel.data.repository.data_sources_implementation.MarvelLocalDataSourceDouble
import com.talentomobile.marvel.data.repository.data_sources_implementation.MarvelRemoteDataSourceDouble
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MarvelRepositoryImplTest  {

    lateinit var sut: MarvelRepositoryImpl
    lateinit var marvelRemoteDataSource: MarvelRemoteDataSourceDouble
    lateinit var marvelLocalDataSource: MarvelLocalDataSourceDouble

    @Before
    fun setUp() {
        marvelRemoteDataSource = MarvelRemoteDataSourceDouble()
        marvelLocalDataSource = MarvelLocalDataSourceDouble()
        sut = MarvelRepositoryImpl(marvelRemoteDataSource, marvelLocalDataSource)
    }

    @Test
    fun get_all_characters_from_api_return_success_response() = runBlocking {
        marvelRemoteDataSource.isSuccessFull = true
        val response = sut.getAllCharacters()
        assertEquals(1, response.data?.get(0)?.id)
    }

    @Test
    fun get_all_characters_from_api_return_error_response() = runBlocking {
        marvelRemoteDataSource.isSuccessFull = false
        val response = sut.getAllCharacters()
        assertEquals(null, response.message)
    }
}