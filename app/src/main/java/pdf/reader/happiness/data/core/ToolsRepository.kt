package pdf.reader.happiness.data.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.data.room.dao.ToolsDao

interface ToolsRepository {

    fun fetchSearchResult(query: String):Flow<List<InfoModel>>
    fun fetchFavorites():Flow<List<InfoModel>>

    class Base(private val toolsDao: ToolsDao): ToolsRepository {
        override fun fetchSearchResult(query:String): Flow<List<InfoModel>> {
            return toolsDao.fetchSearchResult(query).map { it.map { it.mapToInfoModel() } }
        }

        override fun fetchFavorites(): Flow<List<InfoModel>> {
            return toolsDao.fetchFavorites().map { it.map { it.mapToInfoModel() } }
        }

    }
}