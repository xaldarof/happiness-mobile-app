package pdf.reader.happiness.data.cache.settings_cache

import pdf.reader.happiness.data.cache.core.ToolsRepository

interface AllChaptersFinished {

    suspend fun isAllChaptersFinished():Boolean

    class Base(private val toolsRepository: ToolsRepository): AllChaptersFinished {

        override suspend fun isAllChaptersFinished(): Boolean {
            var isAllFinished = true
            toolsRepository.fetchAll().forEach {
                if (!it.finished) isAllFinished = false
            }
            return isAllFinished
        }
    }
}