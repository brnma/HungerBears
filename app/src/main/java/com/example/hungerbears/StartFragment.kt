package com.example.hungerbears

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.hungerbears.databinding.FragmentStartBinding
import org.json.JSONArray
import org.json.JSONObject

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private var numberOfUsers: Int = 1
    private var numberOfRestaurants: Int = 1

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.startButton.setOnClickListener() {
            viewModel.setNumRestaurants(numberOfRestaurants)
            viewModel.setNumUsers(numberOfUsers - 1)

            // call google maps api to get restaurants
//            GoogleMapsService(this.requireContext()).getRestaurants()
            getRestaurants()

            //navigate to buffer screen
            findNavController().navigate(R.id.action_startFragment_to_bufferFragment)
        }

        binding.minusButton1.setOnClickListener() {
            if (numberOfUsers > 1) {
                numberOfUsers--
                binding.numUsersVal.text = numberOfUsers.toString()
            }
        }

        binding.plusButton1.setOnClickListener() {
            numberOfUsers++
            binding.numUsersVal.text = numberOfUsers.toString()
        }

        binding.minusButton2.setOnClickListener() {
            if (numberOfRestaurants > 1) {
                numberOfRestaurants--
                binding.numRestaurantsVal.text = numberOfRestaurants.toString()
            }
        }

        binding.plusButton2.setOnClickListener() {
            if (numberOfRestaurants < 20) {
                numberOfRestaurants++
                binding.numRestaurantsVal.text = numberOfRestaurants.toString()
            }
        }

        //TODO: store "number of" inputs in ViewModel
        //TODO: store radius in ViewModel

        return binding.root
    }


    private val BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
    //    private val API_KEY = "TEMP"
    private val API_KEY = "AIzaSyASOVUElVvEKOaRxZpUWhX8pDySvM3n8DE"

    fun getRestaurants(){
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
                for (i in 0..viewModel.getNumRestaurants() - 1) {
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
                    val photoUrl =
                        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=${photoRef}&key=${API_KEY}"
                    //TODO: modify Restaurant class fields? (address OR lat/lng, photoUrl ...)

//                    viewModel.setLat(lat)
//                    viewModel.setLng(lng)

                    val distance = viewModel.getDistance()
                    val restaurantObj = Restaurant()
                    restaurantObj.setItem(name, photoUrl, distance, rating.toFloat())
                    //TODO: add restaurantObj to list in ViewModel
                    viewModel.addRestaurant(restaurantObj)
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