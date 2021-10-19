package pdf.reader.happiness.data.cache.settings_cache

import android.content.SharedPreferences

interface WastedTimeAchievementController {

    fun isCongratulated(ach:String):Boolean
    fun setCongratulated(ach: String,congratulated: Boolean)

    class Base(private val sharedPreferences: SharedPreferences): WastedTimeAchievementController {

        override fun isCongratulated(ach: String): Boolean {
            return sharedPreferences.getBoolean(ach,false)
        }

        override fun setCongratulated(ach: String,congratulated: Boolean) {
            sharedPreferences.edit().putBoolean(ach,congratulated).apply()
        }
    }
}