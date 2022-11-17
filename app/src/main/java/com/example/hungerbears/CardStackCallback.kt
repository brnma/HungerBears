package com.example.hungerbears

import androidx.recyclerview.widget.DiffUtil

class CardStackCallback(old: List<Restaurant>, new: List<Restaurant>) :
    DiffUtil.Callback() {
    private val old: List<Restaurant>
    private val new: List<Restaurant>

    init {
        this.old = old
        this.new = new
    }

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].getImage() === new[newItemPosition].getImage()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] === new[newItemPosition]
    }
}
