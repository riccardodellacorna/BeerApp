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

    val liveDataBeer = MutableLiveData<Beer>()

    fun fetchBeerResponse() = viewModelScope.launch {
        val resource : Resource<Beer> = repository.getRandomBeer()

        when (resource) {
            is Resource.Success -> {
                liveDataBeer.postValue(resource.data)
            }
            is Resource.Error -> {
                //TODO()
            }
        }
    }
}
