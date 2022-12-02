package com.example.hungerbears

import android.location.Location
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private var numUsers: Int = 0
    private var usersComp: Int = 0
    private var numRestaurant: Int = 0

    private var restLat: Double = 0.0
    private var restLng: Double = 0.0

    private var userLat: Double = 0.0
    private var userLng: Double = 0.0

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

    fun getNumRestaurants(): Int{
        return numUsers
    }

    fun setNumRestaurants(num: Int){
        numRestaurant = num
    }

    fun setNumUsers(num: Int){
        numUsers = num
    }

    fun getNumUsers():Int{
        return numUsers
    }

    fun setRestLat(lat: Double){
        restLat = lat
    }

    fun setRestLng(lng: Double){
        restLng = lng
    }

    fun setUserLat(lat: Double){
        userLat = lat
    }

    fun setUserLng(lng: Double){
        userLng = lng
    }

    fun getUserLat(): Double{
        return userLat
    }

    fun getUserLng(): Double{
        return userLng
    }

    fun incrementUsersComp(){
        usersComp++
    }

    fun getDistance(): Float {
        // calculate distance
        //TODO: update user lat and lng to not be hardcoded
        val userLocation = Location("User Location")
//                    userLocation.setLatitude(userlat)
//                    userLocation.setLongitude(userlng)
        userLocation.latitude = 37.229572
        userLocation.longitude = -80.413940
        val restaurantLocation = Location("Restaurant Location")
        restaurantLocation.latitude = restLat
        restaurantLocation.longitude = restLng

        return (userLocation.distanceTo(restaurantLocation)) / 1609.344f
    }

    fun finishedUsers(): Boolean{
        return if (usersComp == numUsers) {
            true
        } else {
            incrementUsersComp()
            false
        }
    }
}