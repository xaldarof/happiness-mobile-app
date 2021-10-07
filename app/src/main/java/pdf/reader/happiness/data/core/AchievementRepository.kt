package pdf.reader.happiness.data.core

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.data.cache.data_source.AchievementDataSource

interface AchievementRepository {

    suspend fun fetchAchievements(): Flow<List<AchievementModel>>
    fun insertAchievement(achievementModel: AchievementModel)

    class Base(private val achievementDataSource: AchievementDataSource) : AchievementRepository {

        override suspend fun fetchAchievements(): Flow<List<AchievementModel>> {
            return achievementDataSource.fetchAchievements()
        }

        override fun insertAchievement(achievementModel: AchievementModel) {
            achievementDataSource.insertAchievement(achievementModel)
        }
    }
}