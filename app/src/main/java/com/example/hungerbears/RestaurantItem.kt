package com.example.hungerbears
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "restaurant_table")
data class RestaurantItem(@PrimaryKey @ColumnInfo(name ="place_id") var id: String,
                     @ColumnInfo(name ="name") var name: String,
                     @ColumnInfo(name ="rating") var rating: Long,
                     @ColumnInfo(name ="photo_ref") var photo_ref: String,
)