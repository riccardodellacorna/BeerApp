package com.example.beerapp.UI

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.beerapp.Data.Beer
import com.example.beerapp.databinding.SingleBeerRowBinding

class BeerListAdapter() : RecyclerView.Adapter<BeerListAdapter.ViewHolder> (){
    private var beerList: List<Beer> = listOf()
    var onItemClick: ((Beer) -> Unit)? = null

    inner class ViewHolder(val binding: SingleBeerRowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(beerList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleBeerRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.beerDescriptionView.text = beerList[position].tagline
        viewHolder.binding.beerNameView.text = beerList[position].name
        viewHolder.binding.beerImageView.load(beerList[position].image_url)
    }

    override fun getItemCount() = beerList.size

    fun setBeers(beerListPassed : List<Beer>){
        if(beerListPassed != beerList) {
            val sizeOld = beerList.size
            beerList = beerListPassed
            val sizeNew = beerList.size

            Log.d("FRAG", "adapter - oldlist size: $sizeOld")
            Log.d("FRAG", "adapter - newlist size: $sizeNew")
            notifyItemRangeInserted(sizeOld + 1, sizeNew)
        }
    }
}

