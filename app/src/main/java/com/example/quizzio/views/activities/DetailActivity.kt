package com.example.quizzio.views.activities

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizzio.R
import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.utils.AppConstants
import com.example.quizzio.utils.ResourceUtils
import com.example.quizzio.views.fragments.EntertainmentFragment
import com.example.quizzio.views.fragments.HomeFragment
import com.example.quizzio.views.ui.CategoryItemType
import com.example.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.example.quizzio.views.viewmodels.HomeViewModel

class DetailActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel
    var categoryTag:String?=null
    var colorId:Int=R.color.entertainment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val extras = intent.extras
        val type = extras?.getParcelable<CategoryItemType>(AppConstants.categoryType)
        categoryTag = extras?.getString("category")

        val tag :String?
        val fragment: Fragment
        colorId = type!!.color

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ResourceUtils.toColor(colorId)))
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,HomeFragment.getInstance())
                .commit()
        }
    }

    fun getcategory(): String? {
        Log.d("Detail Activity", "$categoryTag")
        return categoryTag
    }

    fun getColor(): Int? {
        return colorId
    }
}
