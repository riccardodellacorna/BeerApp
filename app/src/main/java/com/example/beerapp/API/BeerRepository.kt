package com.example.beerapp.API

import com.example.beerapp.Data.Beer
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val beerAPI: BeerAPI
) {
    suspend fun getRandomBeer(): Resource<Beer> {
        beerAPI.getBeer().let {
            if (it.isSuccessful) {
                if (it.body().isNullOrEmpty())
                    return Resource.Error<Beer>(120)
                val random_beer: Beer = Beer.createBeer(it.body()!!.first())
                return Resource.Success<Beer>(random_beer)
            }
            return Resource.Error<Beer>(120)
        }
    }
}