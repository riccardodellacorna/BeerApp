package com.example.beerapp.API

import com.example.beerapp.Data.BeerAPI
import com.example.beerapp.Data.Constants
import retrofit2.Response
import retrofit2.http.GET

interface BeerAPI {
    @GET(Constants.RANDOM_BEERS_URL)
    suspend fun getBeer(): Response<List<BeerAPI>>

//    @GET(Constants.BEERS_URL)
//    suspend fun getAllBeers(): Response<List<BeerAPI>>

    @GET(Constants.PAGINATION_BEERS_URL)
    suspend fun getAllBeers(): Response<List<BeerAPI>>
}