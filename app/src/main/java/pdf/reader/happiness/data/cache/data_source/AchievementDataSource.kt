package pdf.reader.happiness.data.cache.data_source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.data.cache.dao.AchievementDao

interface AchievementDataSource {

    suspend fun fetchAchievements(): Flow<List<AchievementModel>>
    fun insertAchievement(achievementModel: AchievementModel)

    class Base(private val achievementDao: AchievementDao) : AchievementDataSource {

        init {
            achievementDao.insertAchievement(
                AchievementModel(
                    "Спасибо, что скачали наше приложение.",
                    System.currentTimeMillis(), AchievementModel.AchievementType.TIME_WASTED
                ).mapToAchievementDb()
            )
        }

        override suspend fun fetchAchievements(): Flow<List<AchievementModel>> {
            return achievementDao.fetchAchievements()
                .map { it -> it.map { it.mapToAchievementModel() } }
        }

        override fun insertAchievement(achievementModel: AchievementModel) {
            achievementDao.insertAchievement(achievementModel.mapToAchievementDb())
        }
    }
}