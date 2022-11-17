package com.example.hungerbears


class Restaurant {
    private var image = 0
    private var distanceAway: String = ""
    private var name: String = ""
    private var rating: Float = 0f


    fun setItem(name: String, image: Int, dist: String, rating: Float){
        this.name = name
        this.image = image
        distanceAway = dist
        this.rating = rating
    }

    fun getImage(): Int{
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