package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.data.cache.core.ToolsRepository

class SearchViewModel(private val toolsRepository: ToolsRepository): ViewModel() {

    fun fetchSearchResult(query:String) = toolsRepository.fetchSearchResult(query).asLiveData()

}