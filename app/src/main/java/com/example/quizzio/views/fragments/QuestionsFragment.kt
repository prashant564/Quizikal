package com.example.quizzio.views.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quizzio.R
import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.network.Resource
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.utils.AppConstants
import com.example.quizzio.utils.ResourceUtils
import com.example.quizzio.views.activities.DetailActivity
import com.example.quizzio.views.adapters.TriviaListAdapter
import com.example.quizzio.views.listeners.RecyclerItemClickListener
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.example.quizzio.views.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_questions.*
import kotlinx.android.synthetic.main.fragment_questions.view.*

/**
 * A simple [Fragment] subclass.
 */
class QuestionsFragment : Fragment() {
    var category:String?=null
    var colorId:Int=R.color.entertainment
    lateinit var triviaListAdapter: TriviaListAdapter
    val viewmodel by lazy {
        val repository = TriviaRepository(TriviaDatabase.invoke(activity!!.applicationContext))
        var factory = TriviaViewModelFactory(repository,category!!)
        ViewModelProviders.of(this,factory).get(HomeViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_questions, container, false)
        category = arguments!!.getString(AppConstants.categoryTag)
        colorId = arguments!!.getInt(AppConstants.colorId)
        triviaListAdapter = TriviaListAdapter(listener)
        root.rv_trivia.adapter=triviaListAdapter
        viewmodel.allTrivia.observe(viewLifecycleOwner,
            Observer { response ->
                when(response){
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        response.data?.let {
                            hideProgressBar()
                            triviaListAdapter.submitList(it)
                        }
                    }
                    is Resource.Failure -> {
                        hideProgressBar()
                        showToast(getString(R.string.something_went_wrong))
                    }
                }
            }
        )
        return root
    }

    private val listener = RecyclerItemClickListener{
        when(it.id){
            R.id.cv_main->{
                val tag = it.tag as TriviaUI
                (activity as DetailActivity).navigateToAnswerFragment(tag)
            }
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        progressBar.indeterminateDrawable
            .setColorFilter(ResourceUtils.toColor(colorId), PorterDuff.Mode.SRC_IN )
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message , Toast.LENGTH_LONG).show()
    }
}
