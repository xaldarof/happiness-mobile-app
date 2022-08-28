package pdf.reader.happiness.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pdf.reader.happiness.data.cache.initilizers.AllInitializer
import pdf.reader.happiness.data.cache.settings_cache.WastedTimeController
import pdf.reader.happiness.data.cache.settings_cache.WastedTimeAchievement
import pdf.reader.happiness.data.cache.settings_cache.WastedTimeAchievementController
import pdf.reader.happiness.data.cloud.user.UserRepository

class MainActivityViewModel(
    private val allInitializer: AllInitializer,
    private val wastedTimeController: WastedTimeController,
    private val wastedTimeAchievementController: WastedTimeAchievementController,
    private val wastedTimeAchievement: WastedTimeAchievement,
    private val userRepository: UserRepository
) : ViewModel() {


    suspend fun updateWastedTime() {
        while (true) {
            wastedTimeController.updateWastedTime()
            Log.d("pos", wastedTimeController.getTime().toString())
            delay(3000)
        }
    }

    init {
        viewModelScope.launch {
            userRepository.syncUser(onSuccess = {
                Log.d("res", "Synced")
            }, onFail = {

            })
        }

        allInitializer.invokeAll()
    }

    fun startWastingTime() {
        if (wastedTimeController.getTime() > 12000 && !wastedTimeAchievementController.isCongratulated(
                "1"
            )
        ) {
            wastedTimeAchievement.addAchievementWasted1HourTime()
            wastedTimeAchievementController.setCongratulated("1", true)
        }

        if (wastedTimeController.getTime() > 23000 && !wastedTimeAchievementController.isCongratulated(
                "2"
            )
        ) {
            wastedTimeAchievement.addAchievementWasted2HourTime()
            wastedTimeAchievementController.setCongratulated("2", true)
        }

        if (wastedTimeController.getTime() > 50000 && !wastedTimeAchievementController.isCongratulated(
                "3"
            )
        ) {
            wastedTimeAchievement.addAchievementWasted3HourTime()
            wastedTimeAchievementController.setCongratulated("3", true)
        }
    }
}