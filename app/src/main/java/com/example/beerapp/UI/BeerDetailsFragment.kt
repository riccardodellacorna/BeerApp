package com.example.beerapp.UI

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.beerapp.Data.Beer
import com.example.beerapp.MainActivityInterface
import com.example.beerapp.MainViewModel
import com.example.beerapp.R
import com.example.beerapp.databinding.FragmentBeerDetailsBinding
import com.example.beerapp.databinding.FragmentBeerListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailsFragment : Fragment() {
    private lateinit var mainActivityInterface : MainActivityInterface
    private val mainViewModel : MainViewModel by activityViewModels()
    private var _binding: FragmentBeerDetailsBinding? = null
    private val binding get() = _binding!!
    //var onItemClick: ((Beer) -> Unit)? = null

    companion object {
        fun newInstance(): BeerDetailsFragment {
            return BeerDetailsFragment()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityInterface) mainActivityInterface = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener() //click
    }

    private fun setOnClickListener() {
        view?.setOnClickListener {
            Log.d("FRAG","click on Details")
            mainActivityInterface?.goToList()
        }
    }


}