package pdf.reader.happiness.data.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.data.room.dao.ToolsDao

interface AchievementRepository {

    suspend fun fetchAchievements(): Flow<List<AchievementModel>>
    fun insertAchievement(achievementModel: AchievementModel)

    class Base(private val toolsDao: ToolsDao) : AchievementRepository {

        init {
            toolsDao.insertAchievement(
                AchievementModel("Спасибо, что скачали наше приложение.", System.currentTimeMillis(), AchievementModel.AchievementType.TIME_WASTED).mapToAchievementDb())
        }

        override suspend fun fetchAchievements(): Flow<List<AchievementModel>> {
            return toolsDao.fetchAchievements().map { it -> it.map { it.mapToAchievementModel() } }
        }

        override fun insertAchievement(achievementModel: AchievementModel) {
            toolsDao.insertAchievement(achievementModel.mapToAchievementDb())
        }
    }
}