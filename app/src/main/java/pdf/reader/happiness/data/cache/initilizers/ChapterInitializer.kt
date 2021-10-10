package pdf.reader.happiness.data.cache.initilizers

import pdf.reader.happiness.data.cache.core.ChaptersRepository
import pdf.reader.happiness.data.cache.dao.CoreDao
import pdf.reader.happiness.data.cache.models.Type

interface ChapterInitializer {

    suspend fun update()

    class Base(
        private val chaptersRepository: ChaptersRepository,
        private val coreDao: CoreDao
    ) : ChapterInitializer {
        override suspend fun update() {
            coreDao.fetchHappyCount(Type.HAPPY).forEach {
                if (!it.finished) {
                    chaptersRepository.updateChapterFinishedState(false, "СЧАСТЬЕ")
                    chaptersRepository.updateChapterCongratulated(false,"СЧАСТЬЕ")
                }
            }
            coreDao.fetchLifeCount(Type.LIFE).forEach {
                if (!it.finished) {
                    chaptersRepository.updateChapterFinishedState(false, "ЖИЗНЬ")
                    chaptersRepository.updateChapterCongratulated(false,"ЖИЗНЬ")
                }
            }
            coreDao.fetchLoveCount(Type.LOVE).forEach {
                if (!it.finished) {
                    chaptersRepository.updateChapterFinishedState(false, "ЛЮБОВЬ")
                    chaptersRepository.updateChapterCongratulated(false,"ЛЮБОВЬ")
                }
            }
            coreDao.fetchSuccessCount(Type.SUCCESS).forEach {
                if (!it.finished) {
                    chaptersRepository.updateChapterFinishedState(false, "УСПЕХ")
                    chaptersRepository.updateChapterCongratulated(false,"УСПЕХ")
                }
            }
        }
    }
}