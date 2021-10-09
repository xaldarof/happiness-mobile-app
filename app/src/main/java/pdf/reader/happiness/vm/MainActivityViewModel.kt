package pdf.reader.happiness.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import pdf.reader.happiness.data.cache.settings_cache.WastedTimeController
import pdf.reader.happiness.tools.WastedTimeAchievement
import pdf.reader.happiness.tools.WastedTimeAchievementController

class MainActivityViewModel(
    private val wastedTimeController: WastedTimeController,
    private val wastedTimeAchievementController: WastedTimeAchievementController,
    private val wastedTimeAchievement: WastedTimeAchievement
) : ViewModel() {


    suspend fun updateWastedTime(){
        while (true){
            delay(3000)
            wastedTimeController.updateWastedTime()
            Log.d("pos",wastedTimeController.getTime().toString())
        }
    }

    fun startWastingTime() {
        if (wastedTimeController.getTime()>3600 && !wastedTimeAchievementController.isCongratulated("1")){
            wastedTimeAchievement.addAchievementWasted1HourTime()
            wastedTimeAchievementController.setCongratulated("1",true)
        }

        if (wastedTimeController.getTime()>7200 && !wastedTimeAchievementController.isCongratulated("2")){
            wastedTimeAchievement.addAchievementWasted2HourTime()
            wastedTimeAchievementController.setCongratulated("2",true)
        }

        if (wastedTimeController.getTime()>10000 && !wastedTimeAchievementController.isCongratulated("3")){
            wastedTimeAchievement.addAchievementWasted3HourTime()
            wastedTimeAchievementController.setCongratulated("3",true)
        }
    }
}