package com.example.hungerbears

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.hungerbears.databinding.FragmentMatchBinding
import com.example.hungerbears.databinding.FragmentSelectionBinding


class MatchFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentMatchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMatchBinding.inflate(inflater, container, false)
        val matchedRestaurant = viewModel.getMatch()
        binding.matchText.text = matchedRestaurant.getName()
        context?.let { Glide.with(it).load(matchedRestaurant.getImage()).fitCenter().into(binding.matchImage) }
        binding.directionsButton.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("google.navigation:q=" + matchedRestaurant.getLocation().latitude + "," + matchedRestaurant.getLocation().longitude)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
        return binding.root
    }

}