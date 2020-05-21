package com.example.quizzio.views.activities

import android.content.Intent
import android.os.Bundle
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

        categoryItemAdapter.submitList(categoryList)
    }


    val listener = RecyclerItemClickListener{
        when(it.id){
            R.id.cv_main->{
                val tag = it.tag as CategoryItem
                when(tag.itemType) {
                    CategoryItemType.CATEGORY_MUSIC->
                    navigateToDetailActivity(tag)
                }
            }
        }
    }


    private fun navigateToDetailActivity(tag: CategoryItem){
        val bundle = Bundle()
        bundle.putParcelable(AppConstants.categoryType,tag.itemType)
        bundle.putString(AppConstants.categoryTag,tag.tag)
        startDetailActivity(bundle)
    }

    private fun startDetailActivity(bundle: Bundle){
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtras(bundle)
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


