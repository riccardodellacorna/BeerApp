package com.example.beerapp.API

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val beerService: BeerService) {
    suspend fun getBeer() =
        beerService.getBeer()
}