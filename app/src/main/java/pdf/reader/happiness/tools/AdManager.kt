package pdf.reader.happiness.tools

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

interface Showable {
    fun setUpAd()
    fun showAd()
    fun setUseRealId(state:Boolean)
}

class AdManager(private val context: Context) : Showable {
    private var mInterstitialAd: InterstitialAd? = null
    private var useRealId = false

    override fun setUpAd() {
        val adRequest = AdRequest.Builder().build()
        val testId = "ca-app-pub-3940256099942544/1033173712"
        val realId = "ca-app-pub-6592657632288197/8633125415"
        InterstitialAd.load(
            context,
            if (useRealId) realId else testId, adRequest,

            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                    Log.d("pos2", "FAIL AD")
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    Log.d("pos2", "SUCCESS AD")
                }
            })
    }

    override fun showAd() {
        mInterstitialAd?.show(context as Activity)
    }

    override fun setUseRealId(state: Boolean) {
        useRealId = state
    }
}