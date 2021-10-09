package pdf.reader.happiness.data.cache.settings_cache

import android.content.SharedPreferences

interface CongratulationController {

    private companion object{
        const val HAPPY_CONGR = "happy_cong"
        const val LIFE_CONGR = "life_cong"
        const val SUCCESS_CONGR = "success_cong"
        const val LOVE_CONGR = "love_cong"
        const val ALL_CONGR = "all_cong"
    }

    fun setHappyCongratulated(state: Boolean)
    fun isHappyCongratulated():Boolean

    fun setSuccessCongratulated(state: Boolean)
    fun isSuccessCongratulated():Boolean

    fun setLiveCongratulated(state: Boolean)
    fun isLifeCongratulated():Boolean

    fun setLoveCongratulated(state: Boolean)
    fun isLoveCongratulated():Boolean

    fun isAllCongratulated():Boolean
    fun setAllCongratulated(state: Boolean)


    class Base(private val sharedPreferences: SharedPreferences):CongratulationController {
        override fun setHappyCongratulated(state: Boolean) {
            sharedPreferences.edit().putBoolean(HAPPY_CONGR,state).apply()
        }

        override fun isHappyCongratulated(): Boolean = sharedPreferences.getBoolean(HAPPY_CONGR,false)

        override fun setSuccessCongratulated(state: Boolean) {
            sharedPreferences.edit().putBoolean(SUCCESS_CONGR,state).apply()
        }

        override fun isSuccessCongratulated(): Boolean = sharedPreferences.getBoolean(SUCCESS_CONGR,false)

        override fun setLiveCongratulated(state: Boolean) {
            sharedPreferences.edit().putBoolean(LIFE_CONGR,state).apply()
        }

        override fun isLifeCongratulated(): Boolean = sharedPreferences.getBoolean(LIFE_CONGR,false)

        override fun setLoveCongratulated(state: Boolean) {
            sharedPreferences.edit().putBoolean(LOVE_CONGR,state).apply()
        }

        override fun isLoveCongratulated(): Boolean  = sharedPreferences.getBoolean(LOVE_CONGR,false)


        override fun isAllCongratulated(): Boolean = sharedPreferences.getBoolean(ALL_CONGR,false)

        override fun setAllCongratulated(state: Boolean) {
            sharedPreferences.edit().putBoolean(ALL_CONGR,state).apply()
        }

    }
}