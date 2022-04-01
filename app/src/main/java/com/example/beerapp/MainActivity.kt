package com.example.beerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.beerapp.UI.BeerListFragment
import com.example.beerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding


    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        mainViewModel
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, BeerListFragment.newInstance(), "beerList")
                .commit()
        }

        //fetchData()

        //mainViewModel.liveDataBeer.observe(this){
        //    _binding.ciao.text = it.name
        //}
    }

    //private fun fetchData() {
    //    mainViewModel.fetchBeerResponse()  //prendo la risposta dalla repo
    //    val stringa : String = mainViewModel.toString()
    //}

    //private fun fetchResponse() {
        //mainViewModel.fetchBeerResponse()
        //binding.pbBeer.visibility = View.VISIBLE
    //}
}