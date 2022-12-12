package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGysumBinding
import com.example.myapplication.databinding.RecyclerviewItemBinding

class Gysum_Fragment : Fragment() {

    private lateinit var binding: FragmentGysumBinding
    private lateinit var binding2: RecyclerviewItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gysum, container, false)
        binding2 = DataBindingUtil.inflate(inflater, R.layout.recyclerview_item, container, false)

        binding2.imgRvPhoto.setOnClickListener {
            Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show()
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}