package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import pdf.reader.happiness.data.core.DataRepository

class ReadingViewModel(private val dataRepository: DataRepository): ViewModel() {

    suspend fun updateFavorite(body:String,favorite:Boolean) {
        dataRepository.updateLifeFavorite(body,favorite)
    }
}