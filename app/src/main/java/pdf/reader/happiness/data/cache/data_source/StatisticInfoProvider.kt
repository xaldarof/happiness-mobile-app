package pdf.reader.happiness.data.cache.data_source

import pdf.reader.happiness.data.cache.dao.CoreDao
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cache.settings_cache.WastedTimeController
import pdf.reader.happiness.tools.toClock

interface StatisticInfoProvider {

    suspend fun fetchHappyWastedTime():Float
    suspend fun fetchLifeWastedTime():Float
    suspend fun fetchLoveWastedTime():Float
    suspend fun fetchSuccessWastedTime():Float

    suspend fun fetchWastedTime():String

    fun fetchHappyFinishedCount():Int
    fun fetchLifeFinishedCount():Int
    fun fetchSuccessFinishedCount():Int
    fun fetchLoveFinishedCount():Int

    class Base(private val coreDao: CoreDao,
               private val wastedTimeController: WastedTimeController):
        StatisticInfoProvider {

        override fun fetchHappyFinishedCount():Int{
            var finishedCount = 0
            coreDao.fetchInfoCountByType(Type.HAPPY).forEach {
                if (it.finished) finishedCount++
            }
            return finishedCount
        }

        override fun fetchLifeFinishedCount():Int{
            var finishedCount = 0
            coreDao.fetchInfoCountByType(Type.LIFE).forEach {
                if (it.finished) finishedCount++
            }
            return finishedCount
        }

        override fun fetchSuccessFinishedCount():Int{
            var finishedCount = 0
            coreDao.fetchInfoCountByType(Type.SUCCESS).forEach {
                if (it.finished) finishedCount++
            }
            return finishedCount
        }

        override fun fetchLoveFinishedCount():Int{
            var finishedCount = 0
            coreDao.fetchInfoCountByType(Type.LOVE).forEach {
                if (it.finished) finishedCount++
            }
            return finishedCount
        }

        override suspend fun fetchHappyWastedTime(): Float {
            var time = 0f
            coreDao.fetchInfoCountByType(Type.HAPPY).forEach {
                time+=it.readTimeSeconds
            }

            return time
        }


        override suspend fun fetchLifeWastedTime(): Float {
            var time = 0f
            coreDao.fetchInfoCountByType(Type.LIFE).forEach {
                time+=it.readTimeSeconds
            }
            return time
        }

        override suspend fun fetchLoveWastedTime(): Float {
            var time = 0f
            coreDao.fetchInfoCountByType(Type.LOVE).forEach {
                time+=it.readTimeSeconds
            }
            return time
        }

        override suspend fun fetchSuccessWastedTime(): Float {
            var time = 0f
            coreDao.fetchInfoCountByType(Type.SUCCESS).forEach {
                time+=it.readTimeSeconds
            }
            return time
        }

        override suspend fun fetchWastedTime(): String {
            return wastedTimeController.getTime().toClock()
        }
    }
}