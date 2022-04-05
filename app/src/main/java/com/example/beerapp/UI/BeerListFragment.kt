package com.example.beerapp.UI

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beerapp.MainActivityInterface
import com.example.beerapp.MainViewModel
import com.example.beerapp.R
import com.example.beerapp.databinding.FragmentBeerListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerListFragment : Fragment() {

    private var mainActivityInterface : MainActivityInterface? = null
    private val mainViewModel : MainViewModel by activityViewModels()
    private var _binding: FragmentBeerListBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.
    private val beerListAdapter = BeerListAdapter()

    //gestire nel viewModel
    private var pageNum = 1
    var page = -1

    companion object {
        fun newInstance(): BeerListFragment {
            return BeerListFragment()
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
        _binding = FragmentBeerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.beerListRecyclerView.adapter = beerListAdapter
        val lmanager  = LinearLayoutManager(requireContext())
        binding.beerListRecyclerView.layoutManager = lmanager

        fetchResponse()

        //SET LISTENERS
        setOnClickListener() //click
        setScrollListener(lmanager)  //scroll
    }

    private fun setOnClickListener() {
        beerListAdapter.onItemClick = { beer ->
            Log.d("FRAG", beer.name)

            //TODO:continua da qua!
            mainViewModel.fetchBeerDetails(beer)

            mainActivityInterface?.goToDetails(beer.id.toInt())
        }
    }

    private fun setScrollListener( lmanager : LinearLayoutManager) {
        binding.beerListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount = lmanager.childCount
                val pastVisibleItem = lmanager.findFirstCompletelyVisibleItemPosition()
                val total = beerListAdapter.itemCount
                Log.d("FRAG", "on scrolled: total $total , visible $visibleItemCount , past visible: $pastVisibleItem")

                super.onScrolled(recyclerView, dx, dy)

                //TODO
                if (dy>0) {
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        pageNum++ //TODO: gestisci nel viewModel
                        fetchMoreData() //TODO: gestisci nel ViewModel
                        Log.d("FRAG", "ohhghg")
                    }
                }
            }
        })
    }

    private fun fetchMoreData(){
        //TODO
    }

    private fun fetchResponse() {
        mainViewModel.fetchBeerResponse()
        mainViewModel.liveDataBeerList.observe(viewLifecycleOwner){
            it?.let {
                beerListAdapter.setBeers(it)
            }
        }
    }
}