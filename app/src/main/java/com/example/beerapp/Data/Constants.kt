package com.example.beerapp.Data

//  classe per gestire le costanti

class Constants {
    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
        const val BEERS_URL = "beers"
        const val RANDOM_BEERS_URL = "beers/random"
        const val PAGINATION_BEERS_URL = "beers?page=2&per_page=30"
    }
}