package pdf.reader.happiness.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cloud.CloudDataRepository

class ImportingActivityViewModel(
    private val cloudDataRepository: CloudDataRepository,
    private val cacheRepository: CacheDataRepository
) : ViewModel() {

    suspend fun invoke() {
        val cloudData = cloudDataRepository.fetchCloudData().collect {
            cacheRepository.insertData(it.map { it.mapToCacheInfoModel() })
            Log.d("pos2","VIEWMODEL = $it")
        }
    }
}