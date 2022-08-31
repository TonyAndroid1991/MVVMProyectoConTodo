package com.talentomobile.marvel.presentation.di

import com.talentomobile.marvel.presentation.ui.adapter.HomeRecyclerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesHomeRecyclerAdapter(): HomeRecyclerAdapter {
        return HomeRecyclerAdapter()
    }
}