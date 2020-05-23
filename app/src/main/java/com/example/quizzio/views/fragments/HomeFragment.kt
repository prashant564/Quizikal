package com.example.quizzio.views.fragments

import android.os.Bundle
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
import com.example.quizzio.network.Resource
import com.example.quizzio.views.activities.DetailActivity
import com.example.quizzio.views.adapters.TriviaListAdapter
import com.example.quizzio.views.listeners.RecyclerItemClickListener
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.example.quizzio.views.viewmodels.HomeViewModel


class HomeFragment : Fragment() {
    companion object {
        fun getInstance() = HomeFragment()
    }

    var colorId: Int? = R.color.art_and_literature
    lateinit var triviaListAdapter: TriviaListAdapter
    var categoryTag:String?=null

    private val viewModel by lazy {
        val repository = TriviaRepository(TriviaDatabase.invoke(this.context))
        val factory = TriviaViewModelFactory(repository, categoryTag!!)
        ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        categoryTag = (activity as DetailActivity).getcategory()
        colorId = (activity as DetailActivity).getColor()
        triviaListAdapter = TriviaListAdapter(listener)
        binding.apply {
            lifecycleOwner=this@HomeFragment
            viewModel=viewModel
            rvTriviaList.adapter = triviaListAdapter
        }
        viewModel.getAllTrivia()
        viewModel.allTrivia.observe(viewLifecycleOwner,
            Observer { response ->
                when(response){
                    is Resource.Success -> {
                        response.data?.let {
                            triviaListAdapter.submitList(it)
//                            Log.d("Home Fragment","$it")
                        }
                    }
                }
            }
        )

        return binding.root
    }

    val listener = RecyclerItemClickListener{
        when(it.id){
            R.id.cl_main->{
                val tag = it.tag as TriviaUI
            }
        }
    }

}
