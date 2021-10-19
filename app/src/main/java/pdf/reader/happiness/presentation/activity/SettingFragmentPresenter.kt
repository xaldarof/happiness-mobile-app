package pdf.reader.happiness.presentation.activity

import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.runBlocking
import pdf.reader.happiness.data.cache.settings_cache.AllChaptersFinished
import pdf.reader.happiness.data.cache.settings_cache.FontController
import pdf.reader.happiness.data.cache.settings_cache.ThemeController

class SettingFragmentPresenter(private val view: SettingsView,
                               private val themeController: ThemeController,
                               private val fontController: FontController,
                               private val allChaptersFinished: AllChaptersFinished){

    fun updateDarkThemeSwitchState(state:Boolean) {
        themeController.setTheme(state)
        if (state) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        view.updateDarkThemeSwitchState(state)
    }

    fun updateFontSwitchState(state: Boolean){
        fontController.setBoldFont(state)
        view.updateFontSwitchState(state)
    }

    fun isAllChaptersFinished():Boolean = runBlocking {
        return@runBlocking allChaptersFinished.isAllChaptersFinished()
    }

    interface SettingsView {
        fun updateDarkThemeSwitchState(state: Boolean)
        fun updateFontSwitchState(state: Boolean)
    }
}