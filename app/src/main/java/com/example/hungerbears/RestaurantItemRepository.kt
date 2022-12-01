package com.example.hungerbears

import androidx.annotation.WorkerThread
import androidx.room.Dao

class RestaurantItemRepository(private val restaurantDao: RestaurantItemDao) {
    val allRestaurants: List<RestaurantItem> = restaurantDao.getAllRestaurants()
}