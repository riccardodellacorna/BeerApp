package com.example.beerapp.API

sealed class Resource<T>{
    class Success<T>(val data: T): Resource<T>(){

    }
    class Error<T>(val error : Int) : Resource<T>() {
    }

}