package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import pdf.reader.happiness.core.StatisticModel
import pdf.reader.happiness.data.cache.core.StatisticRepository

class StatisticViewModel(private val statisticRepository: StatisticRepository): ViewModel() {

    suspend fun fetchStatistic():List<StatisticModel>{
        return statisticRepository.fetchStatistic()
    }

    suspend fun fetchHappyWastedTime(): Float = statisticRepository.fetchHappyWastedTime()
    suspend fun fetchLifeWastedTime(): Float = statisticRepository.fetchHappyWastedTime()
    suspend fun fetchLoveWastedTime(): Float = statisticRepository.fetchLoveWastedTime()
    suspend fun fetchSuccessWastedTime(): Float = statisticRepository.fetchSuccessWastedTime()
}