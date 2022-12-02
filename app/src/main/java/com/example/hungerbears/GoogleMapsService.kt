package com.example.hungerbears

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class GoogleMapsService(val context: Context) {
    private val BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
//    private val API_KEY = "TEMP"
        private val API_KEY = "AIzaSyASOVUElVvEKOaRxZpUWhX8pDySvM3n8DE"

    fun getRestaurants() {
        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(context)
        val url =
            "${BASE_URL}location=37.229572,-80.413940&radius=20000&type=restaurant&key=${API_KEY}"
        //"${BASE_URL}location=${lat},${lng}&radius=${radius}&type=restaurant&key=${API_KEY}"

        // Request a string response from the provided URL
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { res ->
                val results: JSONArray = res.getJSONArray("results")
                for (i in 0..results.length()) {
                    val restaurant: JSONObject = results.getJSONObject(i)
                    val name: String = restaurant.getString("name")
                    val rating: Double = restaurant.getDouble("rating")
                    val address: String = restaurant.getString("vicinity")
                    val lat: Double = restaurant.getJSONObject("geometry").getJSONObject("location")
                        .getDouble("lat")
                    val lng: Double = restaurant.getJSONObject("geometry").getJSONObject("location")
                        .getDouble("lng")
                    val photoRef: String = restaurant.getJSONArray("photos").getJSONObject(0)
                        .getString("photo_reference")
                    // this needs testing -- response is html so might need to view header "location" for URL
                    val photoUrl: String =
                        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=${photoRef}&key=${API_KEY}"
                    //TODO: modify Restaurant class fields? (address OR lat/lng, photoUrl ...)
                    val restaurantObj = Restaurant().setItem(name, 0, "", rating.toFloat())
                    //TODO: add restaurantObj to list in ViewModel
                }

                // testing
//                val restaurant: JSONObject = results.getJSONObject(0)
//                val name: String = restaurant.getString("name")
//                Toast.makeText(context, "Response is: $name", Toast.LENGTH_SHORT).show()
            },
            { error ->
                Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
            })

        // Add the request to the RequestQueue
        queue.add(request)
    }
}