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
import com.example.beerapp.MainViewModel
import com.example.beerapp.R
import com.example.beerapp.databinding.FragmentBeerListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerListFragment : Fragment() {

    private val mainViewModel : MainViewModel by activityViewModels()
    private var _binding: FragmentBeerListBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.
    private val beerListAdapter = BeerListAdapter()

    private var pageNum = 1
    var page = -1
    var isLoading = false

    companion object {
        fun newInstance(): BeerListFragment {
            return BeerListFragment()
        }
    }

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
        binding.beerListRecyclerView.adapter = beerListAdapter
        fetchResponse()
        setScrollListener()
    }

    private fun setScrollListener() {   //listener per gestire lo scroll e cambiare pagina quando non scrilla piu
        //TODO: NON FUNZIONA
        binding.beerListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val layoutManager = LinearLayoutManager(context)
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = beerListAdapter.itemCount
                Log.d("FRAG", "on scrolled: total $total , visible $visibleItemCount , past visible: $pastVisibleItem")

                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading) {
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        pageNum++
                        fetchMoreData()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}