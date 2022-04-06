package com.example.beerapp

import android.util.Log
import androidx.lifecycle.*
import com.example.beerapp.API.BeerRepository
import com.example.beerapp.API.Resource
import com.example.beerapp.Data.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: BeerRepository,
) : ViewModel() {

    val liveDataBeerList = MutableLiveData<List<Beer>>()
    var pageNum = 1
    var perPage = 25
    var isLoading = false

    fun fetchBeerResponse() = viewModelScope.launch {
        val resource : Resource<List<Beer>> = repository.getBeerList(pageNum, perPage)
        when (resource) {
            is Resource.Success -> {
                liveDataBeerList.postValue(resource.data)
            }
            is Resource.Error -> {
                //TODO
            }
        }
    }

    fun openNextPage(){
        if (!isLoading){
            Log.d("FRAG", "page num: $pageNum")
            isLoading = true
            pageNum++
            fetchNextBeerResponse()
        }
    }

    fun fetchNextBeerResponse() = viewModelScope.launch {
        val resource: Resource<List<Beer>> = repository.getBeerList(pageNum, perPage)
        when (resource) {
            is Resource.Success -> {
                liveDataBeerList.postValue(resource.data)
                val nameFirstBeer = liveDataBeerList.value?.get(0)?.name
                Log.d("FRAG", "page num post Success: $pageNum , name first beer: $nameFirstBeer")
                isLoading = false
            }
            is Resource.Error -> {
                //TODO
            }
        }
    }

    //TODO: fai funzione che notifica il fatto che ho raggiunto la fine della pagina
}
