package com.example.beerapp.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.beerapp.MainViewModel
import com.example.beerapp.R
import com.example.beerapp.databinding.FragmentBeerListBinding
import dagger.hilt.android.AndroidEntryPoint

//  classe che rappresenta il Fragment che gestisce la view della lista delle birre

@AndroidEntryPoint
class BeerListFragment : Fragment() {

    private val mainViewModel : MainViewModel by activityViewModels()
    private var _binding: FragmentBeerListBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val beerListAdapter = BeerListAdapter()

    companion object {
        fun newInstance(): BeerListFragment {
            return BeerListFragment()
        }
    }

    //Creates the view for the fragment.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()

        binding.beerListRecyclerView.adapter = beerListAdapter
        /*
        val a = requireActivity().findViewById<RecyclerView>(R.id.beerListRecyclerView)
        a.adapter =beerListAdapter
         */

        mainViewModel.liveDataBeerList.observe(viewLifecycleOwner){
            //richiama l'adapter per settare la view
            beerListAdapter.setBeers(it)
        }
        //mainViewModel.liveDataBeer.observe(viewLifecycleOwner){
        //    binding.ciaoo.text = it.name
        //}

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchData() {
        mainViewModel.fetchBeerResponse()  //prendo la risposta dalla repo
        //val stringa : String = mainViewModel.toString()
        //Log.d("FRAG", "$b")
    }
}