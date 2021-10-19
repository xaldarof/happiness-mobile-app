package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.core.ChaptersRepository
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.settings_cache.AchievementUpdater
import pdf.reader.happiness.tools.PercentCalculator

class LoveViewModel(private val cacheDataRepository: CacheDataRepository,
                    private val chaptersRepository: ChaptersRepository,
                    private val percentCalculator: PercentCalculator,
                    private val achievementUpdater: AchievementUpdater
): ViewModel() {

    suspend fun fetchLove() = cacheDataRepository.fetchLove().asLiveData()


    fun updateChapterFinishedState(list: List<InfoModel>, name:String){
        CoroutineScope(Dispatchers.IO).launch {
            if (percentCalculator.calculateIsAllFinished(list)) {
                chaptersRepository.updateChapterFinishedState(true, name)
            }
        }
    }

    fun updateChapterProgress(list: List<InfoModel>, chapterModel: ChapterModel,callback: CallBack) {
        CoroutineScope(Dispatchers.IO).launch {
            chaptersRepository.updateChapterProgress(percentCalculator.calculatePercent(list), chapterModel.name)

            if (percentCalculator.calculatePercent(list)>99f && !chapterModel.isCongratulated){
                chaptersRepository.updateAllChapterFinished(true,chapterModel.name)
                chaptersRepository.updateChapterCongratulated(true,chapterModel.name)
                callback.chapterFinished()
                achievementUpdater.addAchievementAllLoveFinished()
            }
        }
    }

    interface CallBack{
        fun chapterFinished()
    }
}