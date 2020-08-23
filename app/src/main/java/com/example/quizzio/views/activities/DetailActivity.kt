package com.example.quizzio.views.activities

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quizzio.R
import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.network.Resource
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.utils.AppConstants
import com.example.quizzio.utils.ResourceUtils
import com.example.quizzio.views.adapters.TriviaListAdapter
import com.example.quizzio.views.fragments.AnswerFragment
import com.example.quizzio.views.fragments.FavoriteFragment
import com.example.quizzio.views.fragments.QuestionsFragment
import com.example.quizzio.views.listeners.RecyclerItemClickListener
import com.example.quizzio.views.ui.CategoryItemType
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.example.quizzio.views.viewmodels.HomeViewModel

class DetailActivity : AppCompatActivity() {

    var categoryTag:String?=null
    var colorId:Int=R.color.entertainment
    var fragmentTag:String? = null
    lateinit var triviaListAdapter: TriviaListAdapter
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val extras = intent.extras
        fragmentTag = extras?.getString(AppConstants.fragmentTag)
        when(fragmentTag){
            AppConstants.FragmentTag.QuestionsFragment -> {
                val type = extras?.getParcelable<CategoryItemType>(AppConstants.categoryType)
                categoryTag = extras?.getString("category")
                colorId = type!!.color
                supportActionBar?.setBackgroundDrawable(ColorDrawable(ResourceUtils.toColor(colorId)))
                supportActionBar?.title=categoryTag
                val fragment = QuestionsFragment()
                val bundle = Bundle()
                bundle.putInt(AppConstants.colorId,colorId)
                bundle.putString(AppConstants.categoryTag,categoryTag)
                fragment.arguments=bundle
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit()
            }
            AppConstants.FragmentTag.FavoritesFragment -> {
                supportActionBar?.title=getString(R.string.favorite)
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container,FavoriteFragment())
                    .commit()
            }
        }
    }

    fun navigateToAnswerFragment(tag: TriviaUI){
        val bundle = Bundle()
        val fragment=AnswerFragment()
        bundle.putParcelable(AppConstants.quizData,tag)
        bundle.putInt(AppConstants.colorId,colorId)
        fragment.arguments=bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}
