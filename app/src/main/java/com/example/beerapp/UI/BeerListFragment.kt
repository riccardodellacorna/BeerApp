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
        val layoutManager  = LinearLayoutManager(requireContext())
        binding.beerListRecyclerView.layoutManager = layoutManager

        mainViewModel.liveDataBeerList.observe(viewLifecycleOwner){
            it?.let {
                beerListAdapter.setBeers(it)
            }
        }

        //LISTENERS
        setOnClickListener() //click
        setScrollListener(layoutManager)  //scroll
    }

    private fun setOnClickListener() {
        beerListAdapter.onItemClick = { beer ->
            val id = beer.id
            Log.d("FRAG", "list fragment - name: $id")
            mainActivityInterface?.goToDetails(beer.id.toInt())
        }
    }

    private fun setScrollListener( layoutManager : LinearLayoutManager) {
        binding.beerListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy>0) {
                    val pastVisibleItems = layoutManager.findLastVisibleItemPosition()
                    val totalItemsCount = layoutManager.itemCount
                    Log.d("FRAG", "on scrolled: total $totalItemsCount , past visible: $pastVisibleItems")

                    if (pastVisibleItems >= totalItemsCount - 1 ){
                        Log.d("FRAG", "on scrolled: carico pagina nuova")
                        openNextPage()
                    }
                }

            }
        })
    }

    fun isLoading () : Boolean{
        return mainViewModel.isLoading
    }

    fun openNextPage(){
        mainViewModel.openNextPage()
    }
}