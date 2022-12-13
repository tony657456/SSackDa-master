package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDmcMetalBinding

class dmc_metal : Fragment() {

    private lateinit var binding: FragmentDmcMetalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dmc_metal, container, false)

        binding.dmcMetal.setOnClickListener {
            Toast.makeText(context, "dmc", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

}