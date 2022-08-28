package pdf.reader.happiness.tools

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import pdf.reader.happiness.BuildConfig

interface Ad {
    suspend fun showRewardedAd()
}

class RewardedAdManager(private val callback: CallBack, private val context: Context) : Ad {

    private var myRewardedAd: RewardedAd? = null
    private val realId = "ca-app-pub-6592657632288197/6785212343"
    private val testId = "ca-app-pub-3940256099942544/5224354917"
    override suspend fun showRewardedAd() {
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(context, if (BuildConfig.DEBUG) testId else realId, adRequest,
            object : RewardedAdLoadCallback() {

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("pos2", adError.message)
                    callback.onNetworkError()
                    myRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    Log.d("pos2", "Ad was loaded.")
                    myRewardedAd = rewardedAd

                    myRewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdShowedFullScreenContent() {
                            Log.d("pos2", "Ad was shown.")
                        }


                        override fun onAdDismissedFullScreenContent() {
                            Log.d("pos2", "Ad was dismissed.")
                            callback.onDismiss()
                            myRewardedAd = null
                        }
                    }
                    if (myRewardedAd != null) {
                        myRewardedAd?.show(context as Activity, OnUserEarnedRewardListener {
                            var rewardAmount = it.amount
                            var rewardType = it.type
                            Log.d("pos2", "User earned the reward.")
                            callback.onSuccessReward()
                        })
                    } else {
                        Log.d("pos2", "The rewarded ad wasn't ready yet.")
                        callback.onAdIsNotReady()
                    }
                }
            })
    }

    interface CallBack {
        fun onSuccessReward()
        fun onNetworkError()
        fun handledAnError()
        fun onDismiss()
        fun onAdIsNotReady()
    }
}
