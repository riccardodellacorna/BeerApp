package com.example.beerapp.UI

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.beerapp.MainActivityInterface
import com.example.beerapp.MainViewModel
import com.example.beerapp.databinding.FragmentBeerDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BeerDetailsFragment : Fragment() {
    private lateinit var mainActivityInterface : MainActivityInterface
    private val mainViewModel : MainViewModel by activityViewModels()
    private var _binding: FragmentBeerDetailsBinding? = null
    private val binding get() = _binding!!

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

        val data = arguments
        val id = data!!.getInt("beerId")
        Log.d("FRAG","on create view details - id: $id ")
        val index = id - 1

        //set view
        mainViewModel.liveDataBeerList.value?.get(index)?.let {
            binding.beerName.text = it.name ?: "null"
            binding.beerDescription.text = it.description ?: "null"
            binding.beerTagline.text = it.tagline ?: "null"
            binding.beerImage.load(it.image_url)
        }

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
            Log.d("FRAG","details Fragment - click on Details")
            mainActivityInterface.goToList()
        }
    }
}