package com.example.hungerbears

import android.location.Location
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private var numUsers: Int = 0
    private var usersComp: Int = 0
    private var numRestaurant: Int = 0

    private var userLat: Double = 0.0
    private var userLng: Double = 0.0

    private var radius: String = ""

    private var votes = Array<Int>(20){0}

    private var restaurantList = ArrayList<Restaurant>()

    fun setRestaurant(restList: ArrayList<Restaurant>){
        restaurantList = restList
    }

    fun getRadius():String{
        return radius
    }

    fun setRadius(num: String){
        radius = num
    }

    fun getRestaurant(): ArrayList<Restaurant> {
//        restaurantList.shuffle()
        return restaurantList
    }

    fun addRestaurant(item: Restaurant){
        restaurantList.add(item)
    }

    fun getNumRestaurants(): Int{
        return numRestaurant
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


    fun finishedUsers(): Boolean{
        return if (usersComp == numUsers) {
            true
        } else {
            incrementUsersComp()
            false
        }
    }

    fun getVotes(): Array<Int>{
        return votes
    }

    fun getMatch(): Restaurant{
        val max: Int = votes.maxOrNull() ?: 0
        val loc = Location("dummyprovider")

        if (max == 0) {
            val noMatch = Restaurant()
            noMatch.setItem("No Match",
                "", 0.toFloat(), loc, loc)

            return noMatch
        }
        else {
            val values = ArrayList<Int>()


            for ((index, element) in votes.withIndex()) {
                if (element == max){
                    values.add(index)
                    println("added $index to array list")
                }
            }
            val matchIndex = values.shuffled()[0]

            return restaurantList[matchIndex]
        }

    }

    fun resetValues(){
        numUsers = 0
        usersComp = 0
        numRestaurant = 0
        userLat = 0.0
        userLng = 0.0
        radius = ""
        votes  = Array<Int>(20){0}
        restaurantList.clear()
    }
}