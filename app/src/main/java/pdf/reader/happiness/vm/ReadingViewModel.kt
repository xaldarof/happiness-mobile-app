package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import pdf.reader.happiness.data.core.DataRepository

class ReadingViewModel(private val dataRepository: DataRepository): ViewModel() {

    suspend fun updateFavorite(body:String,favorite:Boolean) {
        dataRepository.updateFavoriteState(body,favorite)
    }

    suspend fun updateOpened(body:String,favorite:Boolean) {
        dataRepository.updateOpenedState(body,favorite)
    }

    suspend fun updateFinishedState(body: String,finished:Boolean){
        dataRepository.updateFinishedState(body,finished)
    }


}