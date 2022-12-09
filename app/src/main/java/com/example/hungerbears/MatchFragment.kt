package com.example.hungerbears

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.hungerbears.databinding.FragmentMatchBinding
import java.util.*


class MatchFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentMatchBinding? = null
    private val binding get() = _binding!!


    // Declaring sensorManager
    // and acceleration constants
    private var sensorManager: SensorManager? = null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMatchBinding.inflate(inflater, container, false)
        val matchedRestaurant = viewModel.getMatch()

        if(matchedRestaurant.getName() == "No Match"){
            binding.matchTextConstant.text = ""
            binding.matchText.text = "Could Not Find A Match"
            binding.directionsButton.isEnabled = false
            binding.directionsButton.alpha = 0.3f

            context?.let { Glide.with(it).load(R.drawable.sad).fitCenter().into(binding.matchImage) }
        }
        else {
            binding.matchText.text = matchedRestaurant.getName()
            context?.let { Glide.with(it).load(matchedRestaurant.getImage()).fitCenter().into(binding.matchImage) }
        }

        binding.directionsButton.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("google.navigation:q=" + matchedRestaurant.getLocation().latitude + "," + matchedRestaurant.getLocation().longitude)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        binding.startOver.setOnClickListener{
            viewModel.resetValues()
            findNavController().navigate(R.id.action_matchFragment_to_startFragment)
        }

        // Getting the Sensor Manager instance
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        Objects.requireNonNull(sensorManager)!!
            .registerListener(sensorListener, sensorManager!!
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)

        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH

        return binding.root
    }

    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {

            // Fetching x,y,z values
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration

            // Getting current accelerations
            // with the help of fetched x,y,z values
            currentAcceleration = kotlin.math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta

            // Display a Toast message if
            // acceleration value is over 12
            if (acceleration > 20) {
                val v = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                // Vibrate for 300 milliseconds
                // Vibrate for 300 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v!!.vibrate(
                        VibrationEffect.createOneShot(
                            300,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    //deprecated in API 26
                    v!!.vibrate(500)
                }
                Toast.makeText(context, "Random Match!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_matchFragment_self)
            }
        }
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    override fun onResume() {
        sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(
            Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )
        super.onResume()
    }

    override fun onPause() {
        sensorManager!!.unregisterListener(sensorListener)
        super.onPause()
    }

}