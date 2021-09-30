package pdf.reader.happiness.data.settings_cache

import android.content.SharedPreferences

interface FontController {

    fun isBoldFont():Boolean
    fun setBoldFont(state:Boolean)

    class Base(private val sharedPreferences: SharedPreferences): FontController {
        override fun isBoldFont():Boolean{
            return sharedPreferences.getBoolean(BOLD_FONT,false)
        }

        override fun setBoldFont(state: Boolean) {
            sharedPreferences.edit().putBoolean(BOLD_FONT,state).apply()
        }
    }
    private companion object {
        const val BOLD_FONT = "font"
    }
}