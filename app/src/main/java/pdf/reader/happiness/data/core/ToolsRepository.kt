package pdf.reader.happiness.data.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.data.room.dao.ToolsDao

interface ToolsRepository {

    fun fetchSearchResult(query: String):Flow<List<InfoModel>>
    fun fetchFavorites():Flow<List<InfoModel>>


    suspend fun updateFavoriteState(body:String,favorite:Boolean)
    suspend fun updateOpenedState(body: String,opened:Boolean)
    suspend fun updateFinishedState(body: String,finished:Boolean)

    class Base(private val toolsDao: ToolsDao): ToolsRepository {

        override fun fetchSearchResult(query:String): Flow<List<InfoModel>> {
            return toolsDao.fetchSearchResult(query).map { it.map { it.mapToInfoModel() } }
        }

        override fun fetchFavorites(): Flow<List<InfoModel>> {
            return toolsDao.fetchFavorites().map { it.map { it.mapToInfoModel() } }
        }

        override suspend fun updateFavoriteState(body: String, favorite: Boolean) {
            toolsDao.updateFavoriteState(body,favorite)
        }

        override suspend fun updateOpenedState(body: String, opened: Boolean) {
            toolsDao.updateOpenedState(body,opened)
        }

        override suspend fun updateFinishedState(body: String, finished: Boolean) {
            toolsDao.updateFinishedState(body,finished)
        }
    }
}