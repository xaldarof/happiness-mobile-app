package pdf.reader.happiness.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.data.models.Type
import pdf.reader.happiness.data.room.dao.CoreDao

interface SuccessCacheDataSource {

    suspend fun fetchSuccess(): Flow<List<InfoModel>>
    suspend fun updateFavoriteState(body:String,favorite:Boolean)

    class Base(private val dao: CoreDao) : SuccessCacheDataSource {
        override suspend fun fetchSuccess(): Flow<List<InfoModel>> {
            return dao.fetchSuccess(Type.SUCCESS).map {
                it.map { it.mapToInfoModel() }}
        }

        override suspend fun updateFavoriteState(body: String, favorite: Boolean) {
            dao.updateFavoriteState(body,favorite)
        }
    }
}