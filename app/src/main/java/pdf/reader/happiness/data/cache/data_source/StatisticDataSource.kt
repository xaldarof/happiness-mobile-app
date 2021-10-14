package pdf.reader.happiness.data.cache.data_source

import pdf.reader.happiness.core.Name
import pdf.reader.happiness.core.StatisticModel
import pdf.reader.happiness.data.cache.dao.CoreDao
import pdf.reader.happiness.tools.toClock
import pdf.reader.happiness.tools.translateToRu
import kotlin.collections.ArrayList

interface StatisticDataSource {

    suspend fun fetchStatistic(): ArrayList<StatisticModel>

    suspend fun fetchHappyWastedTime():Float
    suspend fun fetchLifeWastedTime():Float
    suspend fun fetchLoveWastedTime():Float
    suspend fun fetchSuccessWastedTime():Float

    class Base(private val coreDao: CoreDao,private val statistic: StatisticInfoProvider) : StatisticDataSource {

        override suspend fun fetchStatistic(): ArrayList<StatisticModel> {
            val finishedData = ArrayList<StatisticModel>()
            val all = coreDao.fetchAll()

            //Enter count
            val maxEnteredInfo = all.sortedByDescending { it.enterCount }
            if (maxEnteredInfo[0].enterCount!=0){
                finishedData.add(StatisticModel(maxEnteredInfo[0].type.translateToRu(),
                    "Статья которая вы больше всего входили для прочтения:",
                    "Название: ${maxEnteredInfo[0].title}"))
            }


            //Max Time Wasted
            val maxReadTime = all.sortedByDescending { it.readTimeSeconds }
            if (maxReadTime[0].readTimeSeconds.toInt()!=0){
                finishedData.add(StatisticModel(maxReadTime[0].type.translateToRu(),
                    "Статья которая вы потратили наибольшее время для прочтения:",
                    "Название: ${maxReadTime[0].title}"))
            }


            //All wasted Time
            finishedData.add(StatisticModel("Все","Время потраченное в приложении:",
                "Счетчик: ${statistic.fetchWastedTime()}"))


            //Real read time
            val readTime = statistic.fetchHappyWastedTime()+ statistic.fetchLifeWastedTime()+
                    statistic.fetchLoveWastedTime()+ statistic.fetchSuccessWastedTime().toInt()
            finishedData.add(StatisticModel("Все","Время потраченное для чтения :",
                "Всего: ${readTime.toInt().toClock()}"))


            //Calculate finished

            finishedData.add(StatisticModel("Все","Количество прочитанных информации :",
                "Счетчик: ${statistic.fetchFinishedCount()}"))

            finishedData.add(StatisticModel(Name.LOVE,"Количество прочитанных информации :",
                    "Прочитано: ${statistic.fetchLoveFinishedCount()}"))

             finishedData.add(StatisticModel(Name.LIFE,"Количество прочитанных информации :",
                 "Прочитано: ${statistic.fetchLifeFinishedCount()}"))

            finishedData.add(StatisticModel(Name.SUCCESS,"Количество прочитанных информации :",
                    "Прочитано: ${statistic.fetchSuccessFinishedCount()}"))

            finishedData.add(StatisticModel(Name.HAPPY,"Количество прочитанных информации :",
                    "Прочитано: ${statistic.fetchHappyFinishedCount()}"))

            return finishedData
        }

        override suspend fun fetchHappyWastedTime(): Float = statistic.fetchHappyWastedTime()
        override suspend fun fetchLifeWastedTime(): Float = statistic.fetchLifeWastedTime()
        override suspend fun fetchLoveWastedTime(): Float =statistic.fetchLoveWastedTime()
        override suspend fun fetchSuccessWastedTime(): Float = statistic.fetchSuccessWastedTime()
    }
}