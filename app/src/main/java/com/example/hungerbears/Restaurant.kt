package com.example.hungerbears

import android.location.Location


class Restaurant {
    private var image = ""
    private var distanceAway: String = ""
    private var name: String = ""
    private var rating: Float = 0f
    private lateinit var userLocation: Location
    private lateinit var restLocation: Location


    fun setItem(name: String, image: String, rating: Float, userLocation: Location, restLocation: Location){
        this.name = name
        this.image = image
        this.rating = rating
        this.userLocation = userLocation
        this.restLocation = restLocation
        calcDistance()

    }

    fun getImage(): String{
        return image
    }

    fun getName(): String{
        return name
    }

    fun getDistanceAway(): String{
        return distanceAway
    }

    fun getRating(): Float{
        return rating
    }

    fun calcDistance() {
        distanceAway =  (String.format("%.2f",(userLocation.distanceTo(restLocation)) / 1609.344f))
    }
}