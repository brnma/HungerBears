package com.example.hungerbears

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hungerbears.databinding.CardViewBinding

class CardStackAdapter(val context: Context, val list: ArrayList<Restaurant>): RecyclerView.Adapter<CardStackAdapter.CardStackViewHolder>() {

    inner class CardStackViewHolder(val binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackViewHolder {


        return CardStackViewHolder(CardViewBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: CardStackViewHolder, position: Int) {
        holder.binding.itemName.text = list[position].getName()
        holder.binding.itemDistance.text = list[position].getDistanceAway()
        holder.binding.itemRating.rating = list[position].getRating()

        holder.binding.itemImage.setImageResource(list[position].getImage())
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
