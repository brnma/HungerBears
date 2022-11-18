package com.example.hungerbears

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.hungerbears.databinding.FragmentSelectionBinding
import com.example.hungerbears.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private var numberOfUsers: Int = 1;
    private var numberOfRestaurants: Int = 1;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.startButton.setOnClickListener() {
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
            numberOfRestaurants++
            binding.numRestaurantsVal.text = numberOfRestaurants.toString()
        }


        return binding.root
    }
}