package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.core.ChaptersRepository
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.tools.PercentCalculator

class LoveViewModel(private val dataRepository: DataRepository,
                    private val chaptersRepository: ChaptersRepository,
                    private val percentCalculator: PercentCalculator
): ViewModel() {

    suspend fun fetchLove() = dataRepository.fetchLove().asLiveData()


    fun updateChapterFinishedState(list: List<InfoModel>, name:String){
        CoroutineScope(Dispatchers.IO).launch {
            if (percentCalculator.calculateIsAllFinished(list)) {
                chaptersRepository.updateChapterFinishedState(true, name)
            }
        }
    }

    fun updateChapterProgress(list: List<InfoModel>, chapterModel: ChapterModel, callback: CallBack) {
        CoroutineScope(Dispatchers.IO).launch {
            chaptersRepository.updateChapterProgress(percentCalculator.calculatePercent(list), chapterModel.name)

            if (percentCalculator.calculatePercent(list)>99f && !chapterModel.isCongratulated){
                chaptersRepository.updateAllChapterFinished(true,chapterModel.name)
                callback.chapterFinished()
            }
        }
    }

    interface CallBack{
        fun chapterFinished()
    }
}