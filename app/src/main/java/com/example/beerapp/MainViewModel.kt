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
    var perPage = 10
    var isLoading = false

    init {
        fetchBeerResponse()
    }

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
        if(!isLoading){  //isLoading = false
            isLoading = true
            Log.d("FRAG", "page num: $pageNum")
            pageNum++
            fetchNextBeerResponse()
        }
    }

    fun fetchNextBeerResponse() = viewModelScope.launch {
        val resource: Resource<List<Beer>> = repository.getBeerList(pageNum, perPage)
        when (resource) {
            is Resource.Success -> {

                // my actual list
                val a = mutableListOf<Beer>()
                a.addAll((liveDataBeerList.value ?: listOf()))
                a.addAll(resource.data)
                
                liveDataBeerList.postValue(a) //aggiorno il live data

                Log.d("FRAG", "new page: $pageNum")
                isLoading = false
            }
            is Resource.Error -> {
                //TODO
            }
        }
    }

    //TODO: fai funzione che notifica il fatto che ho raggiunto la fine della pagina
}
