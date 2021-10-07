package pdf.reader.happiness.tools

import pdf.reader.happiness.data.dao.ChaptersDao
import pdf.reader.happiness.data.dao.ToolsDao
import pdf.reader.happiness.data.settings_cache.CongratulationController
import pdf.reader.happiness.data.settings_cache.FontController
import pdf.reader.happiness.data.settings_cache.ThemeController

interface CacheClear {

    fun clear()

    class Base(
        private val toolsDao: ToolsDao,
        private val fontController: FontController,
        private val themeController: ThemeController,
        private val congratulationController: CongratulationController,
    ) : CacheClear {

        override fun clear() {
            toolsDao.deleteTypes()
            toolsDao.deleteChapters()
            toolsDao.deleteAchievement()

            fontController.setBoldFont(false)
            themeController.setTheme(false)
            congratulationController.setAllCongratulated(false)
            congratulationController.setLoveCongratulated(false)
            congratulationController.setLiveCongratulated(false)
            congratulationController.setSuccessCongratulated(false)
            congratulationController.setHappyCongratulated(false)
        }
    }
}