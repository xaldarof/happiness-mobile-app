package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.data.core.ToolsRepository

class FavoritesViewModel(private val toolsRepository: ToolsRepository): ViewModel() {

    fun fetchFavorites() = toolsRepository.fetchFavorites().asLiveData()
}