package pdf.reader.happiness.data.cache.core

import pdf.reader.happiness.core.StatisticModel
import pdf.reader.happiness.data.cache.data_source.StatisticDataSource

interface StatisticRepository {

    suspend fun fetchStatistic():List<StatisticModel>

    suspend fun fetchHappyWastedTime():Float
    suspend fun fetchLifeWastedTime():Float
    suspend fun fetchLoveWastedTime():Float
    suspend fun fetchSuccessWastedTime():Float

    class Base(private val statisticDataSource: StatisticDataSource):StatisticRepository {
        override suspend fun fetchStatistic(): List<StatisticModel> {
            return statisticDataSource.fetchStatistic()
        }

        override suspend fun fetchHappyWastedTime(): Float = statisticDataSource.fetchHappyWastedTime()
        override suspend fun fetchLifeWastedTime(): Float = statisticDataSource.fetchHappyWastedTime()
        override suspend fun fetchLoveWastedTime(): Float = statisticDataSource.fetchLoveWastedTime()
        override suspend fun fetchSuccessWastedTime(): Float = statisticDataSource.fetchSuccessWastedTime()
    }
}