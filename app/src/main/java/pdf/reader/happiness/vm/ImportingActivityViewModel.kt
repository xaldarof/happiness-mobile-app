package pdf.reader.happiness.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.initilizers.AllInitializer
import pdf.reader.happiness.data.cache.initilizers.ChapterInitializer
import pdf.reader.happiness.data.cloud.CloudDataRepository
import pdf.reader.happiness.data.cloud.models.InfoCloudModel

class ImportingActivityViewModel(
    private val cloudDataRepository: CloudDataRepository,
    private val cacheRepository: CacheDataRepository,
    private val allInitializer: AllInitializer,
    private val chapterInitializer: ChapterInitializer,
) : ViewModel() {

    suspend fun invoke(callBack: CallBack,count: Int) {
        val limitedList = ArrayList<InfoCloudModel>()

        cloudDataRepository.fetchCloudData().collect { cloudData ->
            if (cloudData.isNotEmpty()) {
                for (i in 0 until count){
                    limitedList.add(cloudData[(cloudData.indices).random()])
                }

                cacheRepository.insertData(limitedList.map { it.mapToCacheInfoModel() })
                allInitializer.invokeAll()
                chapterInitializer.update()
            }
            callBack.onSuccessCallBack(limitedList.size)
            limitedList.clear()
        }
    }
    interface CallBack {
        fun onSuccessCallBack(count: Int)
    }
}


