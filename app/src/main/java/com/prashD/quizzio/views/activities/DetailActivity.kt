package com.prashD.quizzio.views.activities

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prashD.quizzio.R
import com.prashD.quizzio.utils.AppConstants
import com.prashD.quizzio.utils.ResourceUtils
import com.prashD.quizzio.views.adapters.TriviaListAdapter
import com.prashD.quizzio.views.fragments.AnswerFragment
import com.prashD.quizzio.views.fragments.FavoriteFragment
import com.prashD.quizzio.views.fragments.QuestionsFragment
import com.prashD.quizzio.views.ui.CategoryItemType
import com.prashD.quizzio.views.ui.TriviaUI
import com.prashD.quizzio.views.viewmodels.HomeViewModel

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
            .add(R.id.fragment_container,fragment)
            .commit()
    }

    fun navigateToQuestionsFragment(){
        val fragment=QuestionsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}
