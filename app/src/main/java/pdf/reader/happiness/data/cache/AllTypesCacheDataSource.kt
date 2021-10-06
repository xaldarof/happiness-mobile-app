package pdf.reader.happiness.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.room.dao.CoreDao

interface AllTypesCacheDataSource {

    fun fetchAllTypes():Flow<List<InfoModel>>

    class Base(private val coreDao: CoreDao): AllTypesCacheDataSource {
        override fun fetchAllTypes(): Flow<List<InfoModel>> {
            return coreDao.fetchAllTypes().map { it.map { it.mapToInfoModel() } }
        }
    }
}