package com.example.beerapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.beerapp.R

//  classe che rappresenta il Fragment che gestisce la view della lista delle birre

class BeerList_Fragment : Fragment() {

    //Method for creating new instances of the fragment.
    companion object {

        fun newInstance(): BeerList_Fragment {
            return BeerList_Fragment()
        }
    }

    //Creates the view for the fragment.
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_beer_list, container, false)
    }
}