package com.example.beerapp.UI

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
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
        viewHolder.binding.beerDescriptionView.text = beerList[position].tagline
        viewHolder.binding.beerNameView.text = beerList[position].name


        //viewHolder.binding.beerImageView.setImageResource(beerList[position].image) = beerList[position].image_url
        /*Glide.with(viewHolder.binding.root.context)
            .load(beerList[position].image_url)
            .into(viewHolder.binding.beerImageView)*/
        viewHolder.binding.beerImageView.load(beerList[position].image_url)

        val size = beerList.size
    }

    override fun getItemCount() = beerList.size

    //crea un funz. notifyChanges per notificare l'Adapter quando ho una nuova cella nella lista
    fun setBeers(beerListPassed : List<Beer>){
        beerList = beerListPassed
        notifyDataSetChanged()
    }
}

