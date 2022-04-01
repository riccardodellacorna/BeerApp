package com.example.beerapp.API

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Singleton
    @Provides
    fun provideRepo(
        beerAPI: BeerAPI
    ): BeerRepository {
        return BeerRepository(beerAPI)
    }


}