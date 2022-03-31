package com.example.beerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import com.example.beerapp.API.NetworkResult
import com.example.beerapp.UI.BeerList_Fragment
import com.example.beerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, BeerList_Fragment.newInstance(), "beerList")
                .commit()
        }

        fetchData()
    }

    private fun fetchData() {
        mainViewModel.fetchBeerResponse()
        mainViewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    // bind data to the view
                    Log.d("NetResults", "successoo!")
                    /*_binding.imgDog.load(
                        response.data.message
                    ) {
                        transformations(RoundedCornersTransformation(16f))
                    }*/
                }
                is NetworkResult.Error -> {
                    // show error message
                    Log.d("NetResults", "errore!")
                }
                is NetworkResult.Loading -> {
                    // show a progress bar
                    Log.d("NetResults", "...loading...")
                }
            }
        }
    }

    private fun fetchResponse() {
        //mainViewModel.fetchBeerResponse()
        //binding.pbBeer.visibility = View.VISIBLE
    }


}