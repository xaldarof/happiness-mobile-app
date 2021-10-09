package pdf.reader.happiness.data.cache.settings_cache

import android.content.SharedPreferences

interface WastedTimeController {

    fun updateWastedTime()

    fun getTime():Int

    fun clearWastedTime()


    class Base(private val sharedPreferences: SharedPreferences): WastedTimeController {

        override fun updateWastedTime() {
            sharedPreferences.edit().putInt(WASTED_TIME,getTime()+3).apply()
        }

        override fun getTime() = sharedPreferences.getInt(WASTED_TIME,0)

        override fun clearWastedTime() {
            sharedPreferences.edit().putInt(WASTED_TIME,0).apply()
        }

    }
    private companion object{
        private const val WASTED_TIME = "wasted_time"
    }
}