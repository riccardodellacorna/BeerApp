package com.example.beerapp.API

import com.example.beerapp.Data.BeerAPI
import com.example.beerapp.Data.Constants
import retrofit2.Response
import retrofit2.http.GET

interface BeerAPI {
    @GET(Constants.RANDOM_URL)
    suspend fun getBeer(): Response<List<BeerAPI>> //function that can be paused and resumed at a later time

    @GET()
    suspend fun getAllBeers(): Response<List<BeerAPI>> //function that can be paused and resumed at a later time
}