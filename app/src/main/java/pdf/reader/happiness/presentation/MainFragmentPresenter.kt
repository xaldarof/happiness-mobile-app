package pdf.reader.happiness.presentation

import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.settings_cache.BadgeController
import pdf.reader.happiness.tools.AchievementUpdater

class MainFragmentPresenter(private val view: MyView,
                            private val achievementUpdater: AchievementUpdater) {

    fun updatePercentLife(list: List<InfoModel>) {
        view.updateLifePercent(calculatePercent(list))
    }

    fun updatePercentHappy(list: List<InfoModel>) {
        view.updateHappyPercent(calculatePercent(list))
    }

    fun updatePercentLove(list: List<InfoModel>) {
        view.updateLovePercent(calculatePercent(list))
    }

    fun updatePercentSuccess(list: List<InfoModel>) {
        view.updateSuccessPercent(calculatePercent(list))
    }

    fun updatePercentCore(list: List<InfoModel>) {
        view.updateCorePercent(calculatePercent(list))
    }

    private fun calculatePercent(list: List<InfoModel>): Float {
        var count = 0
        list.forEach {
            if (it.finished) {
                count++
            }
        }

        return 100 * (count.toFloat() / list.size.toFloat())
    }

    fun updateAllLifeFinished(list: List<InfoModel>) {
        if (calculateIsAllFinished(list)) {
            view.updateAllLifeFinished()
            achievementUpdater.addAchievementAllLifeFinished()
        }
    }

    fun updateAllSuccessFinished(list: List<InfoModel>) {
        if (calculateIsAllFinished(list)) {
            view.updateAllSuccessFinished()
            achievementUpdater.addAchievementAllSuccessFinished()
        }
    }

    fun updateAllHappyFinished(list: List<InfoModel>) {
        if (calculateIsAllFinished(list)) {
            view.updateAllHappyFinished()
            achievementUpdater.addAchievementAllHappyFinished()
        }
    }

    fun updateAllLoveFinished(list: List<InfoModel>) {
        if (calculateIsAllFinished(list)) {
            view.updateAllLoveFinished()
            achievementUpdater.addAchievementAllLoveFinished()
        }
    }


    fun updateAllFinished(list: List<InfoModel>){
        var count = 0
        list.forEach {
            if (!it.finished){
                count++
            }
        }
        if (count==0){
            view.updateAllFinished()
            achievementUpdater.addAchievementAllFinished()
        }
    }

    private fun calculateIsAllFinished(list: List<InfoModel>): Boolean {
        var finishedCounter = 0
        val total = list.size
        list.forEach {
            if (it.finished) {
                finishedCounter++
            }
        }
        return finishedCounter == total
    }

    interface MyView {
        fun updateLifePercent(percent: Float)
        fun updateSuccessPercent(percent: Float)
        fun updateHappyPercent(percent: Float)
        fun updateLovePercent(percent: Float)

        fun updateCorePercent(percent: Float)

        fun updateAllLifeFinished()
        fun updateAllSuccessFinished()
        fun updateAllHappyFinished()
        fun updateAllLoveFinished()

        fun updateAllFinished()
    }
}
