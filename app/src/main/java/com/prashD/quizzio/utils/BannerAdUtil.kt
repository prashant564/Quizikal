package com.prashD.quizzio.utils

import android.app.Activity
import android.util.DisplayMetrics
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

object BannerAdUtil {

    private const val bannerAdId = "ca-app-pub-7898375357923762/1141794341"
    private const val testAdId = "ca-app-pub-3940256099942544/6300978111"

    fun getBannerAdView(activity: Activity): AdView {
        val ad = AdView(activity)
        ad.adSize = getBannerSize(activity)
//        ad.adUnitId = if(BuildConfig.DEBUG) testAdId else bannerAdId
        ad.adUnitId = bannerAdId
        return ad
    }

    fun getAdRequest(): AdRequest {
        return AdRequest.Builder().build()
    }

    private fun getBannerSize(activity: Activity): AdSize {
        val display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        val adWidthPixels = outMetrics.widthPixels

        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)

    }
}