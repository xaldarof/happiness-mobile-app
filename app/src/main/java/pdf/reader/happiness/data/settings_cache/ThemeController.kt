package pdf.reader.happiness.data.settings_cache

import android.content.SharedPreferences

interface ThemeController {

    fun setTheme(theme: Boolean)
    fun isDarkThemeOn():Boolean

    class Base(private val sharedPreferences: SharedPreferences): ThemeController {

        override fun setTheme(theme:Boolean) {
            sharedPreferences.edit().putBoolean(THEME_NAME,theme).apply()
        }

        override fun isDarkThemeOn(): Boolean {
            return sharedPreferences.getBoolean(THEME_NAME,false)
        }
    }
    private companion object {
        const val THEME_NAME = "theme"
    }
}