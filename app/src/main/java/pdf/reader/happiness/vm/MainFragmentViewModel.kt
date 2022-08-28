package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.data.cache.core.ChaptersRepository
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cloud.user.UserRepository

class MainFragmentViewModel(
    private val chaptersRepository: ChaptersRepository,
    private val cacheDataRepository: CacheDataRepository,
) : ViewModel() {


    suspend fun fetchChapters() = chaptersRepository.fetchChapters().asLiveData()

    suspend fun fetchAll() = cacheDataRepository.fetchAllTypes().asLiveData()

}