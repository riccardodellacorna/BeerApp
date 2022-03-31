package com.example.beerapp.API

import com.example.beerapp.Data.Constants
import retrofit2.Response
import retrofit2.http.GET

interface BeerService {
    @GET(Constants.RANDOM_URL)
    suspend fun getBeer(): Response<BeerResponse> //function that can be paused and resumed at a later time
}