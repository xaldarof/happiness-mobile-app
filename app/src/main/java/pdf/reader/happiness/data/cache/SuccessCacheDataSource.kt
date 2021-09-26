package pdf.reader.happiness.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.data.models.SuccessModel
import pdf.reader.happiness.data.room.dao.SuccessDao

interface SuccessCacheDataSource {

    suspend fun fetchSuccess(): Flow<List<InfoModel>>
    suspend fun updateFavoriteState(body:String,favorite:Boolean)

    class Base(private val dao: SuccessDao) : SuccessCacheDataSource {
        override suspend fun fetchSuccess(): Flow<List<InfoModel>> {
            return dao.fetchSuccessData().map { it.map { it.mapToInfoModel() }}
        }

        override suspend fun updateFavoriteState(body: String, favorite: Boolean) {
            dao.updateSuccessState(body,favorite)
        }
    }
}