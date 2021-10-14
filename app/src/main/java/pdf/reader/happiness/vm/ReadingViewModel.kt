package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import pdf.reader.happiness.data.cache.core.ToolsRepository

class ReadingViewModel(private val repository : ToolsRepository): ViewModel() {

    private var userIsReading = false

    suspend fun updateFavorite(body:String,favorite:Boolean) {
        repository.updateFavoriteState(body,favorite)
    }

    suspend fun updateOpened(body:String,favorite:Boolean) {
        repository.updateOpenedState(body,favorite)
    }

    suspend fun updateFinishedState(body: String,finished:Boolean){
        repository.updateFinishedState(body,finished)
    }

    suspend fun updateEnterCount(body: String) {
        repository.updateEnterCount(body)
    }

    suspend fun startCountingSeconds(body: String) {
        userIsReading = true
        while (userIsReading){
            repository.updateReadTimeSeconds(body)
            delay(3000)
        }
    }

    fun setUserIsOff(state:Boolean){
        userIsReading = false
    }
}