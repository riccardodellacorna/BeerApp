package com.example.beerapp.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beerapp.Beer
import com.example.beerapp.databinding.SingleBeerRowBinding
import kotlinx.coroutines.NonDisposableHandle.parent

//  classe Adapter per gestire la RecyclerView nel BeerListFragment
class BeerListAdapter() : RecyclerView.Adapter<BeerListAdapter.ViewHolder> (){

    private val beerList: List<Beer> = listOf()

    class ViewHolder(val binding: SingleBeerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        val binding = SingleBeerRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    //crea un funz. notifyChanges per notificare l'Adapter quando ho una nuova cella nella lista
}

