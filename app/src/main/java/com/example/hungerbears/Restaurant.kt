package com.example.hungerbears


class Restaurant {
    private var image = ""
    private var distanceAway: String = ""
    private var name: String = ""
    private var rating: Float = 0f


    fun setItem(name: String, image: String, dist: Float, rating: Float){
        this.name = name
        this.image = image
        distanceAway = String.format("%.2f", dist) + " miles away"
        this.rating = rating
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
}