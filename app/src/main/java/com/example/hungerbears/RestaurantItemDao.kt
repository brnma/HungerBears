package com.example.hungerbears

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantItemDao{

    @Query("SELECT * FROM restaurant_table")
    fun getAllRestaurants(): List<RestaurantItem>

}