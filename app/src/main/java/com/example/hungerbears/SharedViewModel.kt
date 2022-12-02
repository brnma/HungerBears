package com.example.hungerbears

import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private var numUsers: Int = 0
    private var numRestaurant: Int = 0

    private var restaurantList = ArrayList<Restaurant>()

    fun setRestaurant(restList: ArrayList<Restaurant>){
        restaurantList = restList
    }

    fun getRestaurant(): ArrayList<Restaurant> {
        return restaurantList
    }

    fun addRestaurant(item: Restaurant){
        restaurantList.add(item)
    }
}