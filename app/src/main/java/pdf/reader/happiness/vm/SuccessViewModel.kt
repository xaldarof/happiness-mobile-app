package pdf.reader.happiness.vm

import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.core.ChaptersRepository
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.tools.PercentCalculator

class SuccessViewModel(private val repository: DataRepository,
                       private val chaptersRepository: ChaptersRepository,
                       private val percentCalculator: PercentCalculator
                       ): ViewModel() {

    suspend fun fetchSuccess() = repository.fetchSuccess().asLiveData()


    fun updateChapterFinishedState(list: List<InfoModel>, name:String){
        CoroutineScope(Dispatchers.IO).launch {
            if (percentCalculator.calculateIsAllFinished(list)) {
                chaptersRepository.updateChapterFinishedState(true, name)
            }
        }
    }

    fun updateChapterProgress(list: List<InfoModel>, name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            chaptersRepository.updateChapterProgress(percentCalculator.calculatePercent(list), name)
        }
    }
}