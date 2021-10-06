package pdf.reader.happiness.tools

import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.data.core.AchievementRepository

interface AchievementUpdater {

    fun addAchievementAllFinished()
    fun addAchievementAllSuccessFinished()
    fun addAchievementAllLifeFinished()
    fun addAchievementAllLoveFinished()
    fun addAchievementAllHappyFinished()

    fun addAchievementWastedHourTime()

    class Base(private val achievementRepository: AchievementRepository) : AchievementUpdater {
        override fun addAchievementAllFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили все разделы", System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED)
            )
        }

        override fun addAchievementAllSuccessFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили раздел 'Успех'", System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED)
            )
        }

        override fun addAchievementAllLifeFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили раздел 'Жизнь'", System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED)
            )
        }

        override fun addAchievementAllLoveFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили раздел 'Любовь'", System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED))
        }

        override fun addAchievementAllHappyFinished() {
            achievementRepository.insertAchievement(
                AchievementModel("Поздравляем, вы завершили раздел 'Счастье'", System.currentTimeMillis(), AchievementModel.AchievementType.UNIT_FINISHED)
            )
        }

        override fun addAchievementWastedHourTime() {
            achievementRepository.insertAchievement(
                AchievementModel("Вы получили достижение 'Мудрец'", System.currentTimeMillis(), AchievementModel.AchievementType.TIME_WASTED)
            )
        }
    }
}