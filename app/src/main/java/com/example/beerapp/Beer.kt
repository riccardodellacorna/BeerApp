package com.example.beerapp

//  classe Birra

data class Beer(
    val description: String,
    val first_brewed: String,
    val id: Int,
    val image_url: String,
    val name: String,
    val tagline: String
)