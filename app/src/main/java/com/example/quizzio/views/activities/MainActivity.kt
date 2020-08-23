package com.example.quizzio.views.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzio.R
import com.example.quizzio.utils.AppConstants
import com.example.quizzio.views.adapters.CategoryItemAdapter
import com.example.quizzio.views.listeners.RecyclerItemClickListener
import com.example.quizzio.views.ui.CategoryItem
import com.example.quizzio.views.ui.CategoryItemType
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var categoryItemAdapter: CategoryItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        createList()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun initRecyclerView() {
        categoryItemAdapter = CategoryItemAdapter(listener)
        rv_category_list.adapter = categoryItemAdapter
    }

    private fun createList() {
        val categoryList = ArrayList<CategoryItem>()
        categoryList.add(CategoryItem(getString(R.string.entertainment),CategoryItemType.CATEGORY_ENTERTAINMENT,AppConstants.CategoryTag.Entertainment))
        categoryList.add(CategoryItem(getString(R.string.music),CategoryItemType.CATEGORY_MUSIC,AppConstants.CategoryTag.Music))
        categoryList.add(CategoryItem(getString(R.string.art_and_literature),CategoryItemType.CATEGORY_ART_AND_LITERATURE,AppConstants.CategoryTag.ArtAndLiterature))
        categoryList.add(CategoryItem(getString(R.string.general),CategoryItemType.CATEGORY_GENERAL,AppConstants.CategoryTag.General))
        categoryList.add(CategoryItem(getString(R.string.history_and_holidays),CategoryItemType.CATEGORY_HISTORY_AND_HOLIDAYS,AppConstants.CategoryTag.HistoryAndHolidays))
        categoryList.add(CategoryItem(getString(R.string.kids),CategoryItemType.CATEGORY_KIDS,AppConstants.CategoryTag.Kids))
        categoryList.add(CategoryItem(getString(R.string.language),CategoryItemType.CATEGORY_LANGUAGE,AppConstants.CategoryTag.Language))
        categoryList.add(CategoryItem(getString(R.string.mathematics),CategoryItemType.CATEGORY_MATHEMATICS,AppConstants.CategoryTag.Mathematics))
        categoryList.add(CategoryItem(getString(R.string.people_and_places),CategoryItemType.CATEGORY_PEOPLE_AND_PLACES,AppConstants.CategoryTag.PeopleAndPlaces))
        categoryList.add(CategoryItem(getString(R.string.religion_and_mythology),CategoryItemType.CATEGORY_RELIGION_AND_MYTHOLOGY,AppConstants.CategoryTag.ReligionAndMythology))
        categoryList.add(CategoryItem(getString(R.string.science_and_nature),CategoryItemType.CATEGORY_SCIENCE_AND_NATURE,AppConstants.CategoryTag.ScienceAndNature))
        categoryList.add(CategoryItem(getString(R.string.sports_and_leisure),CategoryItemType.CATEGORY_SPORTS_AND_LEISURE,AppConstants.CategoryTag.SportsAndLeisure))
        categoryList.add(CategoryItem(getString(R.string.tech_and_video_games),CategoryItemType.CATEGORY_TECH_AND_VIDEO_GAMES,AppConstants.CategoryTag.TechAndVideoGames))
        categoryList.add(CategoryItem(getString(R.string.toys_and_games),CategoryItemType.CATEGORY_TOYS_AND_GAMES,AppConstants.CategoryTag.ToysAndGames))
        categoryItemAdapter.submitList(categoryList)
    }

    val listener = RecyclerItemClickListener{
        when(it.id){
            R.id.cv_main->{
                val tag = it.tag as CategoryItem
                navigateToDetailActivity(tag)
            }
        }
    }


    private fun navigateToDetailActivity(tag: CategoryItem){
        val bundle = Bundle()
        bundle.putParcelable(AppConstants.categoryType,tag.itemType)
        bundle.putString("category",tag.tag)
        startDetailActivity(bundle)
    }

    private fun startDetailActivity(bundle: Bundle){
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtras(bundle)
        intent.putExtra(AppConstants.fragmentTag,AppConstants.FragmentTag.QuestionsFragment)
        startActivity(intent)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_sign_out -> {
                signOut()
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_favourites -> {
                val intent = Intent(this,DetailActivity::class.java)
                intent.putExtra(AppConstants.fragmentTag,AppConstants.FragmentTag.FavoritesFragment)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
            }
    }
}


