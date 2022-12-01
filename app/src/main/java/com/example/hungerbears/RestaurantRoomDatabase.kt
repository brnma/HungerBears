package com.example.hungerbears

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RestaurantItem::class], version = 1, exportSchema = false)
abstract class RestaurantRoomDatabase: RoomDatabase(){
    abstract fun restaurantDao(): RestaurantItemDao

    companion object {
        @Volatile
        private var INSTANCE: RestaurantRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): RestaurantRoomDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantRoomDatabase::class.java,
                    "Restaurant_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}