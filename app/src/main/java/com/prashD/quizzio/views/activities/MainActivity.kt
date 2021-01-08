package com.prashD.quizzio.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.prashD.quizzio.R
import com.prashD.quizzio.utils.AppConstants
import com.prashD.quizzio.utils.BannerAdUtil
import com.prashD.quizzio.utils.FirebaseUtils
import com.prashD.quizzio.utils.SharedPrefUtils
import com.prashD.quizzio.views.adapters.CategoryItemAdapter
import com.prashD.quizzio.views.listeners.RecyclerItemClickListener
import com.prashD.quizzio.views.ui.CategoryItem
import com.prashD.quizzio.views.ui.CategoryItemType
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var categoryItemAdapter: CategoryItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        createList()
        loadBanner()
    }

    private fun initRecyclerView() {
        categoryItemAdapter = CategoryItemAdapter(listener)
        rv_category_list.adapter = categoryItemAdapter
    }

    private fun createList() {
        val categoryList = ArrayList<CategoryItem>()
        categoryList.add(
            CategoryItem(
                getString(R.string.entertainment),
                CategoryItemType.CATEGORY_ENTERTAINMENT,
                AppConstants.CategoryTag.Entertainment
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.music),
                CategoryItemType.CATEGORY_MUSIC,
                AppConstants.CategoryTag.Music
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.art_and_literature),
                CategoryItemType.CATEGORY_ART_AND_LITERATURE,
                AppConstants.CategoryTag.ArtAndLiterature
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.general),
                CategoryItemType.CATEGORY_GENERAL,
                AppConstants.CategoryTag.General
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.history_and_holidays),
                CategoryItemType.CATEGORY_HISTORY_AND_HOLIDAYS,
                AppConstants.CategoryTag.HistoryAndHolidays
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.kids),
                CategoryItemType.CATEGORY_KIDS,
                AppConstants.CategoryTag.Kids
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.language),
                CategoryItemType.CATEGORY_LANGUAGE,
                AppConstants.CategoryTag.Language
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.mathematics),
                CategoryItemType.CATEGORY_MATHEMATICS,
                AppConstants.CategoryTag.Mathematics
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.people_and_places),
                CategoryItemType.CATEGORY_PEOPLE_AND_PLACES,
                AppConstants.CategoryTag.PeopleAndPlaces
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.religion_and_mythology),
                CategoryItemType.CATEGORY_RELIGION_AND_MYTHOLOGY,
                AppConstants.CategoryTag.ReligionAndMythology
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.science_and_nature),
                CategoryItemType.CATEGORY_SCIENCE_AND_NATURE,
                AppConstants.CategoryTag.ScienceAndNature
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.sports_and_leisure),
                CategoryItemType.CATEGORY_SPORTS_AND_LEISURE,
                AppConstants.CategoryTag.SportsAndLeisure
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.tech_and_video_games),
                CategoryItemType.CATEGORY_TECH_AND_VIDEO_GAMES,
                AppConstants.CategoryTag.TechAndVideoGames
            )
        )
        categoryList.add(
            CategoryItem(
                getString(R.string.toys_and_games),
                CategoryItemType.CATEGORY_TOYS_AND_GAMES,
                AppConstants.CategoryTag.ToysAndGames
            )
        )
        categoryItemAdapter.submitList(categoryList)
    }

    val listener = RecyclerItemClickListener {
        when (it.id) {
            R.id.cv_main -> {
                val tag = it.tag as CategoryItem
                sendClickEvent(tag.itemType)
                navigateToDetailActivity(tag)
            }
        }
    }

    private fun sendClickEvent(itemType: CategoryItemType) {
        val bundle = Bundle()
        bundle.putString(FirebaseUtils.PARAM.FEATURE_TYPE.name, itemType.type)
        FirebaseUtils.sendClickEvents(FirebaseUtils.Event.FEATURE_CLICK, bundle)
    }

    private fun loadBanner() {
        val bannerAd = BannerAdUtil.getBannerAdView(this)
        val adViewId = 100
        bannerAd.id = adViewId

        val params = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        bannerAd.layoutParams = params
        cl_main.addView(bannerAd)

        val recyclerViewParams = rv_category_list.layoutParams as ConstraintLayout.LayoutParams
        recyclerViewParams.bottomToTop = adViewId
        rv_category_list.layoutParams = recyclerViewParams

        val request = BannerAdUtil.getAdRequest()
        bannerAd.loadAd(request)
    }

    private fun navigateToDetailActivity(tag: CategoryItem) {
        val bundle = Bundle()
        bundle.putParcelable(AppConstants.categoryType, tag.itemType)
        bundle.putString("category", tag.tag)
        startDetailActivity(bundle)
    }

    private fun startDetailActivity(bundle: Bundle) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtras(bundle)
        intent.putExtra(AppConstants.fragmentTag, AppConstants.FragmentTag.QuestionsFragment)
        startActivity(intent)
    }

    private fun openRateUsFragment() {
//        val f = RateUsBottomFragment().apply {
//            isCancelable = false
//        }
//        f.show(supportFragmentManager,"")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_favourites -> {
                FirebaseUtils.sendClickEvent(FirebaseUtils.ACTION.FAVOURITES)
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(
                    AppConstants.fragmentTag,
                    AppConstants.FragmentTag.FavoritesFragment
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val count = SharedPrefUtils.getLaunchCount()
        if (count >= AppConstants.LAUNCH_COUNT) {
            openRateUsFragment()
        } else {
            super.onBackPressed()
        }
    }
}


