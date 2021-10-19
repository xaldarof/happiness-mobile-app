package pdf.reader.happiness.vm

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.core.UserCoinRepository
import pdf.reader.happiness.data.cache.initilizers.AllInitializer
import pdf.reader.happiness.data.cache.initilizers.ChapterInitializer
import pdf.reader.happiness.data.cloud.CloudDataRepository
import pdf.reader.happiness.data.cloud.models.InfoCloudModel

interface Editable{
    fun fetchUserCoinAsLiveData():LiveData<Int>
    fun fetchUserCoinCount() :Int
}


class ImportingActivityViewModel(
    private val cloudDataRepository: CloudDataRepository,
    private val cacheRepository: CacheDataRepository,
    private val allInitializer: AllInitializer,
    private val chapterInitializer: ChapterInitializer,
    private val userCoin: UserCoinRepository
) : ViewModel(),Editable{

    suspend fun startImporting(callBack: CallBack,count: Int) {
        val limitedList = ArrayList<InfoCloudModel>()

        cloudDataRepository.fetchCloudData().collect { cloudData ->
            if (cloudData.isNotEmpty()) {
                if (count > cloudData.size) {
                    callBack.onOutOfBounds(cloudData.size)
                }
                else {
                    for (i in 0 until count) {
                        limitedList.add(cloudData[(cloudData.indices).random()])
                    }
                    cacheRepository.insertData(limitedList.map { it.mapToCacheInfoModel() })
                    allInitializer.invokeAll()
                    chapterInitializer.update()
                    callBack.onSuccessCallBack(limitedList.size)
                    limitedList.clear()
                }
            }
        }
    }

    override fun fetchUserCoinAsLiveData() = userCoin.fetchUserCoinCountAsFlow().asLiveData()

    override fun fetchUserCoinCount() = userCoin.fetchUserCoinCount()

    fun payWithCoin(price:Int){
        userCoin.payWithCoin(price)
    }


    interface CallBack {
        fun onSuccessCallBack(count: Int)
        fun onOutOfBounds(count: Int)
    }
}


