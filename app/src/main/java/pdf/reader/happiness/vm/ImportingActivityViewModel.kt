package pdf.reader.happiness.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.core.ChaptersRepository
import pdf.reader.happiness.data.cache.initilizers.AllInitializer
import pdf.reader.happiness.data.cache.initilizers.ChapterInitializer
import pdf.reader.happiness.data.cloud.CloudDataRepository
import pdf.reader.happiness.tools.PercentCalculator

class ImportingActivityViewModel(
    private val cloudDataRepository: CloudDataRepository,
    private val cacheRepository: CacheDataRepository,
    private val allInitializer: AllInitializer,
    private val chapterInitializer: ChapterInitializer,
) : ViewModel() {

    suspend fun invoke(callBack: CallBack) {
        cloudDataRepository.fetchCloudData().collect { cloudData ->
            if (cloudData.isNotEmpty()) {
                cacheRepository.insertData(cloudData.map { it.mapToCacheInfoModel() })

                allInitializer.invokeAll()
                chapterInitializer.update()
                }
            callBack.callback(cloudData.size)
        }
    }
    interface CallBack {
        fun callback(count: Int)
    }
}


