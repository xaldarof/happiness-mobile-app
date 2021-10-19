package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.core.ChaptersRepository
import pdf.reader.happiness.data.cache.core.ToolsRepository
import pdf.reader.happiness.data.cache.settings_cache.AchievementUpdater
import pdf.reader.happiness.tools.PercentCalculator

class SearchViewModel(private val toolsRepository: ToolsRepository
): ViewModel() {

    fun fetchSearchResult(query:String) = toolsRepository.fetchSearchResult(query).asLiveData()

}