package com.example.beerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.beerapp.UI.BeerDetailsFragment
import com.example.beerapp.UI.BeerListFragment
import com.example.beerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , MainActivityInterface {

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
    }

    override fun goToDetails(id: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, BeerDetailsFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun goToList() {
        Log.d("FRAG","click on Details - gotolist")
        onBackPressed()
    }
}