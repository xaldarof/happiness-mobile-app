package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.core.ChaptersRepository
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.core.UserCoinRepository
import pdf.reader.happiness.data.cache.models.CoinModelDb

class MainFragmentViewModel(
    private val chaptersRepository: ChaptersRepository,
    private val cacheDataRepository: CacheDataRepository,
    private val userCoinRepository: UserCoinRepository
) : ViewModel() {

    init {
        userCoinRepository.initUserCoin()
    }

    suspend fun fetchChapters() = chaptersRepository.fetchChapters().asLiveData()

    suspend fun fetchAll() = cacheDataRepository.fetchAllTypes().asLiveData()

}