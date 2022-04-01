package com.example.beerapp.Data

//  classe Birra

data class Beer(
    val description: String,
    val id: Long,
    val image_url: String?,
    val name: String,
    val tagline: String
){
    companion object {
        fun createBeer(beerReceived: BeerAPI) : Beer {
            return Beer(
                description = beerReceived.description ?: "NO description",
                id = beerReceived.id,
                image_url = beerReceived.imageURL,
                name = beerReceived.name ?: "NO name",
                tagline = beerReceived.tagline ?: "NO tagline"
            )
        }
    }
}

