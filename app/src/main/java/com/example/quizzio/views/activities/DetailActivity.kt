package com.example.quizzio.views.activities

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quizzio.R
import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.network.Resource
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.utils.AppConstants
import com.example.quizzio.utils.ResourceUtils
import com.example.quizzio.views.adapters.TriviaListAdapter
import com.example.quizzio.views.listeners.RecyclerItemClickListener
import com.example.quizzio.views.ui.CategoryItemType
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.example.quizzio.views.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    var categoryTag:String?=null
    var colorId:Int=R.color.entertainment
    lateinit var triviaListAdapter: TriviaListAdapter
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initParcelableItems()
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ResourceUtils.toColor(colorId)))
        supportActionBar?.title=categoryTag
        val repository = TriviaRepository(TriviaDatabase.invoke(this))
        val factory = TriviaViewModelFactory(repository, categoryTag!!)
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)

        triviaListAdapter = TriviaListAdapter(listener)
        rv_trivia.adapter=triviaListAdapter
        viewModel.allTrivia.observe(this,
            Observer { response ->
                when(response){
                    is Resource.Success -> {
                        response.data?.let {
                            triviaListAdapter.submitList(it)
                        }
                    }
                }
            }
        )
    }

    val listener = RecyclerItemClickListener{
        when(it.id){
            R.id.cv_main->{
                val tag = it.tag as TriviaUI
                navigateToAnswerActivity(tag)
            }
        }
    }

    private fun navigateToAnswerActivity(tag: TriviaUI){
        val bundle = Bundle()
        bundle.putParcelable(AppConstants.quizData,tag)
        bundle.putInt(AppConstants.colorId,colorId)
        startAnswerActivity(bundle)
    }

    private fun startAnswerActivity(bundle: Bundle){
        val intent = Intent(this,AnswerActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun initParcelableItems() {
        val extras = intent.extras
        val type = extras?.getParcelable<CategoryItemType>(AppConstants.categoryType)
        categoryTag = extras?.getString("category")
        colorId = type!!.color
    }
}
