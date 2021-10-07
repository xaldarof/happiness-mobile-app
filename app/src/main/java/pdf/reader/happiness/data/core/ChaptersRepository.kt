package pdf.reader.happiness.data.core

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.data.cache.data_source.ChapterDataSource

interface ChaptersRepository {

    suspend fun fetchChapters():Flow<List<ChapterModel>>
    fun updateChapterFinishedState(isFinished:Boolean,chapterName:String)
    fun updateChapterProgress(progress:Float,chapterName:String)
    fun updateAllChapterFinished(isFinished: Boolean,chapterName:String)
    fun updateChapterCongratulated(isCongratulated: Boolean,chapterName:String)

    class Base(private val chapterDataSource: ChapterDataSource): ChaptersRepository {

        override suspend fun fetchChapters() = chapterDataSource.fetchChapter()

        override fun updateChapterFinishedState(isFinished: Boolean, chapterName: String) {
            chapterDataSource.updateChapterFinishedState(isFinished,chapterName)
        }

        override fun updateChapterProgress(progress: Float, chapterName: String) {
            chapterDataSource.updateChapterProgress(progress,chapterName)
        }

        override fun updateAllChapterFinished(isFinished: Boolean, chapterName: String) {
            chapterDataSource.updateAllChapterFinished(isFinished,chapterName)
        }

        override fun updateChapterCongratulated(isCongratulated: Boolean, chapterName: String) {
            chapterDataSource.updateChapterCongratulated(isCongratulated,chapterName)
        }
    }
}