package pdf.reader.happiness.vm

import androidx.lifecycle.*
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.initilizers.AllInitializer
import pdf.reader.happiness.data.cache.initilizers.ChapterInitializer
import pdf.reader.happiness.data.cloud.CoinRepository
import pdf.reader.happiness.data.cloud.models.InfoCloudModel
import pdf.reader.happiness.data.cloud.user.UserRepository

interface Editable {
    fun fetchUserCoinAsLiveData(): LiveData<Int>
    fun fetchUserCoinCount(): Int
}


class ImportingActivityViewModel(
    private val coinRepository: CoinRepository,
    private val cacheRepository: CacheDataRepository,
    private val allInitializer: AllInitializer,
    private val chapterInitializer: ChapterInitializer,
    private val userCoin: UserRepository
) : ViewModel(), Editable {

    suspend fun startImporting(callBack: CallBack, count: Int) {
        val limitedList = ArrayList<InfoCloudModel>()

        coinRepository.fetchCloudData().collect { cloudData ->
            if (cloudData.isNotEmpty()) {
                if (count > cloudData.size) {
                    callBack.onOutOfBounds(cloudData.size)
                } else {
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

    override fun fetchUserCoinAsLiveData() = userCoin.fetchUserBalanceAsFlow().asLiveData()

    override fun fetchUserCoinCount() = userCoin.fetchUserBalance()

    fun payWithCoin(
        price: Int, onSuccess: () -> Unit,
        onFail: (String) -> Unit
    ) {
        userCoin.invokePayment(price, onSuccess = {
            onSuccess()
        }, onFail = {
            onFail(it)
        })
    }


    interface CallBack {
        fun onSuccessCallBack(count: Int)
        fun onOutOfBounds(count: Int)
    }
}


