package pdf.reader.happiness.data.cache.data_source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cache.dao.CoreDao

interface SuccessCacheDataSource {

    suspend fun fetchSuccess(): Flow<List<InfoModel>>

    class Base(private val dao: CoreDao) : SuccessCacheDataSource {
        override suspend fun fetchSuccess(): Flow<List<InfoModel>> {
            return dao.fetchSuccess(Type.SUCCESS).map {
                it.map { it.mapToInfoModel() }}
        }
    }
}