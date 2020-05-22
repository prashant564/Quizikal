package com.example.quizzio.views.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quizzio.R
import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.databinding.HomeFragmentBinding
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.utils.AppConstants
import com.example.quizzio.utils.Resource
import com.example.quizzio.views.activities.DetailActivity
import com.example.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.example.quizzio.views.viewmodels.HomeViewModel


class HomeFragment : Fragment() {

    companion object {
        fun getInstance() = HomeFragment()
    }

    lateinit var viewModel: HomeViewModel
    var colorId: Int? = R.color.art_and_literature

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)

        val categoryTag = (activity as DetailActivity).getcategory()
        colorId = (activity as DetailActivity).getColor()

        val repository = TriviaRepository(TriviaDatabase.invoke(this.context))
        val factory = TriviaViewModelFactory(repository, categoryTag!!)
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getAllTrivia()
        viewModel.allTrivia.observe(viewLifecycleOwner,
            Observer { response ->
                when(response){
                    is Resource.Success -> {
                        response.data?.let {
                            Log.d("Home Fragment","$it")
                        }
                    }
                }

            }
        )
        return binding.root
    }

}
