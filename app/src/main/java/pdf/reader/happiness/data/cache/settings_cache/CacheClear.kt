package pdf.reader.happiness.data.cache.settings_cache

import pdf.reader.happiness.data.cache.dao.ToolsDao

interface CacheClear {

    fun clear()

    class Base(
        private val toolsDao: ToolsDao,
        private val fontController: FontController,
        private val themeController: ThemeController,
        private val congratulationController: CongratulationController,
        private val wastedTimeController: WastedTimeController,
        private val wastedTimeAchievementController: WastedTimeAchievementController
    ) : CacheClear {

        override fun clear() {
            toolsDao.deleteTypes()
            toolsDao.deleteChapters()
            toolsDao.deleteAchievement()

            wastedTimeController.clearWastedTime()
            fontController.setBoldFont(false)
            themeController.setTheme(false)

            wastedTimeAchievementController.setCongratulated("1",false)
            wastedTimeAchievementController.setCongratulated("2",false)
            wastedTimeAchievementController.setCongratulated("3",false)

            congratulationController.setAllCongratulated(false)
            congratulationController.setLoveCongratulated(false)
            congratulationController.setLiveCongratulated(false)
            congratulationController.setSuccessCongratulated(false)
            congratulationController.setHappyCongratulated(false)
        }
    }
}