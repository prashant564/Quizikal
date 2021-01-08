package com.prashD.quizzio.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.prashD.quizzio.R
import com.prashD.quizzio.database.TriviaDatabase
import com.prashD.quizzio.repository.TriviaRepository
import com.prashD.quizzio.views.activities.DetailActivity
import com.prashD.quizzio.views.adapters.FavoriteListAdapter
import com.prashD.quizzio.views.listeners.RecyclerItemClickListener
import com.prashD.quizzio.views.ui.TriviaUI
import com.prashD.quizzio.views.viewmodelFactory.FavoriteViewModelFactory
import com.prashD.quizzio.views.viewmodels.FavoriteViewModel
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
