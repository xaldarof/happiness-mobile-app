package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.data.core.AchievementRepository

class AchievementsViewModel(private val achievementRepository: AchievementRepository): ViewModel() {

    suspend fun fetchAchievements() = achievementRepository.fetchAchievements().asLiveData()

    fun insertAchievement(achievementModel: AchievementModel){
        achievementRepository.insertAchievement(achievementModel)
    }
}