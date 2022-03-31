package com.example.beerapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.beerapp.API.BeerResponse
import com.example.beerapp.API.NetworkResult
import com.example.beerapp.API.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<BeerResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<BeerResponse>> = _response

    fun fetchBeerResponse() = viewModelScope.launch {
        repository.getBeer().collect { values ->
            _response.value = values
        }
    }
}
