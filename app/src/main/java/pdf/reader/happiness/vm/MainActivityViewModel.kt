package pdf.reader.happiness.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.data.core.AchievementRepository
import pdf.reader.happiness.data.settings_cache.BadgeController
import pdf.reader.happiness.data.settings_cache.WastedTimeController
import pdf.reader.happiness.tools.AchievementUpdater

class MainActivityViewModel(
    private val wastedTimeController: WastedTimeController,
    private val badgeController: BadgeController,
    private val achievementUpdater: AchievementUpdater
) : ViewModel() {


    suspend fun updateWastedTime(){
        while (true){
            delay(1000)
            wastedTimeController.updateWastedTime()
            Log.d("pos",wastedTimeController.getTime().toString())
        }
    }

    fun checkWastedTime() {
        if (wastedTimeController.getTime()>700){
            achievementUpdater.addAchievementWastedHourTime()
            badgeController.updateBadge()
        }
    }
}