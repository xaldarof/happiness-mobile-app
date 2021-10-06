package pdf.reader.happiness.data.settings_cache

import android.content.SharedPreferences

interface WastedTimeController {

    fun updateWastedTime()

    fun getTime():Int


    class Base(private val sharedPreferences: SharedPreferences): WastedTimeController {

        override fun updateWastedTime() {
            sharedPreferences.edit().putInt(WASTED_TIME,getTime()+1).apply()
        }

        override fun getTime() = sharedPreferences.getInt(WASTED_TIME,0)

    }
    private companion object{
        private const val WASTED_TIME = "wasted_time"
    }
}