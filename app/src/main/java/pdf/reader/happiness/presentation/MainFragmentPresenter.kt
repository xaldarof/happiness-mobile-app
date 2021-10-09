package pdf.reader.happiness.presentation

import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.settings_cache.CongratulationController
import pdf.reader.happiness.tools.AchievementUpdater
import pdf.reader.happiness.tools.PercentCalculator


class MainFragmentPresenter(private val view: MyView,private val achievementUpdater: AchievementUpdater,private val congratulationController: CongratulationController) {


    fun updateCoreProgress(list: List<InfoModel>) {
        view.updateCoreProgress(PercentCalculator.Base().calculatePercent(list))
        if (PercentCalculator.Base().calculatePercent(list)>=100f && !congratulationController.isAllCongratulated()){
            view.allChaptersFinished()
            achievementUpdater.addAchievementAllFinished()
            congratulationController.setAllCongratulated(true)
        }
    }

    interface MyView {
        fun updateCoreProgress(percent:Float)
        fun allChaptersFinished()
    }
}
