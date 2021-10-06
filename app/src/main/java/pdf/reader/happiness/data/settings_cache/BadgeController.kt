package pdf.reader.happiness.data.settings_cache

import android.content.SharedPreferences

interface BadgeController {

    fun updateBadge()
    fun clearBadge()
    fun getBadge():Int

    class Base(private val sharedPreferences: SharedPreferences): BadgeController{

        override fun updateBadge() {
           sharedPreferences.edit().putInt(BADGE_COUNT,getBadge()+1).apply()
        }

        override fun clearBadge() {
            sharedPreferences.edit().putInt(BADGE_COUNT,0).apply()
        }

        override fun getBadge(): Int = sharedPreferences.getInt(BADGE_COUNT,0)

    }
    private companion object {
        const val BADGE_COUNT = "badge"
    }
}