package com.example.beerapp.API
import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)
