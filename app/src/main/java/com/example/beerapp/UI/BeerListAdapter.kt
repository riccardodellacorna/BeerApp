package com.example.beerapp.UI

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beerapp.Data.Beer
import com.example.beerapp.databinding.SingleBeerRowBinding


class BeerListAdapter() : RecyclerView.Adapter<BeerListAdapter.ViewHolder> (){
    private var beerList: List<Beer> = listOf()

    inner class ViewHolder(val binding: SingleBeerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleBeerRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.beerDescriptionView.text = beerList[position].description
        //viewHolder.binding.beerImageView. = beerList[position].image_url
        viewHolder.binding.beerNameView.text = beerList[position].name
    }

    override fun getItemCount() = beerList.size

    fun setBeers(beerListPassed : List<Beer>){
        beerList = beerListPassed
        Log.d("FRAG", "${beerList[0].description }")
        Log.d("FRAG", "${beerList[1].description }")
    }

    //crea un funz. notifyChanges per notificare l'Adapter quando ho una nuova cella nella lista

}

