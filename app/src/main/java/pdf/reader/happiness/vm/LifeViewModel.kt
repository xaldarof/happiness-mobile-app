package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.core.ChaptersRepository
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.tools.PercentCalculator

class LifeViewModel(private val dataRepository: DataRepository,
                    private val chaptersRepository: ChaptersRepository,
                    private val percentCalculator: PercentCalculator): ViewModel() {

    suspend fun fetchLife() = dataRepository.fetchLife().asLiveData()


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