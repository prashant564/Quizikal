package com.example.quizzio.views.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import com.example.quizzio.R
import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.databinding.ItemFavListBinding
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.views.activities.DetailActivity
import com.example.quizzio.views.adapters.FavoriteListAdapter
import com.example.quizzio.views.listeners.RecyclerItemClickListener
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.views.viewmodelFactory.FavoriteViewModelFactory
import com.example.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.example.quizzio.views.viewmodels.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.favorite_fragment.*
import kotlinx.android.synthetic.main.favorite_fragment.view.*

class FavoriteFragment : Fragment() {
    lateinit var favoriteListAdapter: FavoriteListAdapter
    val viewmodel by lazy {
        val repository = TriviaRepository(TriviaDatabase.invoke(activity!!.applicationContext))
        var factory = FavoriteViewModelFactory(repository)
        ViewModelProviders.of(this,factory).get(FavoriteViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root =  inflater.inflate(R.layout.favorite_fragment, container, false)
        favoriteListAdapter = FavoriteListAdapter(listener)
        root.rv_fav.adapter=favoriteListAdapter
        viewmodel.getAllFavTrivia().observe(viewLifecycleOwner, Observer {
            favoriteListAdapter.submitList(it)
        })
        return root
    }

    val listener = RecyclerItemClickListener{
        when(it.id){
            R.id.cv_main_fav->{
                val tag = it.tag as TriviaUI
                (activity as DetailActivity).navigateToAnswerFragment(tag)
            }
        }
    }
}
