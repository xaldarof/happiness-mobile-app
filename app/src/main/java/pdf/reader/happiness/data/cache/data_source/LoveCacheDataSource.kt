package pdf.reader.happiness.data.cache.data_source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.models.Type
import pdf.reader.happiness.data.dao.CoreDao

interface LoveCacheDataSource {

    fun fetchLove():Flow<List<InfoModel>>

    class Base(private val coreDao: CoreDao) : LoveCacheDataSource {
        override fun fetchLove(): Flow<List<InfoModel>> {
            return coreDao.fetchLove(Type.LOVE).map {
                it.map { it.mapToInfoModel() }
            }
        }
    }
}