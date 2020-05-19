package com.example.quizzio.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quizzio.databinding.HomeFragmentBinding
import com.example.quizzio.network.TriviaUI
import com.example.quizzio.views.viewmodels.HomeViewModel


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.properties.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty()) {
               Log.d("Home Fragment","Nothing to show")
            } else {

            }
        })

        return binding.root
    }

}
