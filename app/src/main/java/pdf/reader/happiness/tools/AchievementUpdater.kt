package pdf.reader.happiness.tools

import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.data.core.AchievementRepository

interface AchievementUpdater {

    fun addAchievementAllFinished()
    fun addAchievementAllSuccessFinished()
    fun addAchievementAllLifeFinished()
    fun addAchievementAllLoveFinished()
    fun addAchievementAllHappyFinished()


    class Base(private val achievementRepository: AchievementRepository): AchievementUpdater{
        override fun addAchievementAllFinished() {
            achievementRepository.insertAchievement(AchievementModel("Поздравляем, вы завершили все разделы",System.currentTimeMillis()))
        }

        override fun addAchievementAllSuccessFinished() {
            achievementRepository.insertAchievement(AchievementModel("Поздравляем, вы завершили раздел 'Успех'",System.currentTimeMillis()))
        }

        override fun addAchievementAllLifeFinished() {
            achievementRepository.insertAchievement(AchievementModel("Поздравляем, вы завершили раздел 'Жизнь'",System.currentTimeMillis()))
        }

        override fun addAchievementAllLoveFinished() {
            achievementRepository.insertAchievement(AchievementModel("Поздравляем, вы завершили раздел 'Любовь'",System.currentTimeMillis()))
        }

        override fun addAchievementAllHappyFinished() {
            achievementRepository.insertAchievement(AchievementModel("Поздравляем, вы завершили раздел 'Счастье'",System.currentTimeMillis()))
        }

    }
}