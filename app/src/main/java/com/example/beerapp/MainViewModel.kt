package com.example.beerapp

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

    fun fetchBeerResponse() = viewModelScope.launch {
        val resource : Resource<List<Beer>> = repository.getBeerList()
        when (resource) {
            is Resource.Success -> {
                liveDataBeerList.postValue(resource.data)
            }
            is Resource.Error -> {
                //TODO
            }
        }
    }

    fun fetchBeerDetails(beer: Beer){

    }



    //TODO: fai funzione che notifica il fatto che ho raggiunto la fine della pagina
}
