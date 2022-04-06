package com.example.beerapp.API

import com.example.beerapp.Data.BeerAPI
import com.example.beerapp.Data.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerAPI {
    @GET(Constants.RANDOM_BEERS_URL)
    suspend fun getBeer(): Response<List<BeerAPI>>

//    @GET(Constants.BEERS_URL)
//    suspend fun getAllBeers(): Response<List<BeerAPI>>

    @GET(Constants.PAGINATION_BEERS_URL)
    suspend fun getAllBeers(): Response<List<BeerAPI>>

    @GET(Constants.BEERS_URL) //path: 'beers?page=2&per_page=25
    suspend fun getBeers(
        @Query("page") page : Int,
        @Query("per_page") per_page : Int
    ): Response<List<BeerAPI>>
}