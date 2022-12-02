package com.example.hungerbears

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController



class BufferFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_buffer, container, false)
        // Start button section
        val start: Button = view.findViewById(R.id.startButton2)
        start.setOnClickListener() {
            findNavController().navigate(R.id.action_bufferFragment_to_selectionFragment)
        }

        return view
    }

}