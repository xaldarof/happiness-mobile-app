package pdf.reader.happiness.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.data.models.Type
import pdf.reader.happiness.data.room.dao.CoreDao

interface LifeCacheDataSource {

    suspend fun fetchLife(): Flow<List<InfoModel>>

    class Base(private val coreDao: CoreDao): LifeCacheDataSource {
        override suspend fun fetchLife() : Flow<List<InfoModel>> {
            return coreDao.fetchLife(Type.LIFE).map {
               it.map { it.mapToInfoModel() }
            }
        }
    }
}