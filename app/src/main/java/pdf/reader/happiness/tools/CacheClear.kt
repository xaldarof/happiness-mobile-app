package pdf.reader.happiness.tools

import pdf.reader.happiness.data.cache.dao.ToolsDao
import pdf.reader.happiness.data.cache.settings_cache.CongratulationController
import pdf.reader.happiness.data.cache.settings_cache.FontController
import pdf.reader.happiness.data.cache.settings_cache.ThemeController
import pdf.reader.happiness.data.cache.settings_cache.WastedTimeController

interface CacheClear {

    fun clear()

    class Base(
        private val toolsDao: ToolsDao,
        private val fontController: FontController,
        private val themeController: ThemeController,
        private val congratulationController: CongratulationController,
        private val wastedTimeController: WastedTimeController
    ) : CacheClear {

        override fun clear() {
            toolsDao.deleteTypes()
            toolsDao.deleteChapters()
            toolsDao.deleteAchievement()


            wastedTimeController.clearWastedTime()
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