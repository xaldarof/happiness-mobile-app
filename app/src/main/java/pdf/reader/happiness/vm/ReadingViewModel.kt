package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import pdf.reader.happiness.data.cache.core.ToolsRepository

class ReadingViewModel(private val repository : ToolsRepository): ViewModel() {

    suspend fun updateFavorite(body:String,favorite:Boolean) {
        repository.updateFavoriteState(body,favorite)
    }

    suspend fun updateOpened(body:String,favorite:Boolean) {
        repository.updateOpenedState(body,favorite)
    }

    suspend fun updateFinishedState(body: String,finished:Boolean){
        repository.updateFinishedState(body,finished)
    }


}