package pdf.reader.happiness.data.cache.data_source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.dao.CoreDao
import pdf.reader.happiness.data.cache.models.InfoModelDb

interface AllTypesCacheDataSource {

    fun fetchAllTypes():Flow<List<InfoModel>>

    suspend fun insertData(list: List<InfoModelDb>)

    class Base(private val coreDao: CoreDao): AllTypesCacheDataSource {
        override fun fetchAllTypes(): Flow<List<InfoModel>> {
            return coreDao.fetchAllTypes().map { it.map { it.mapToInfoModel() } }
        }

        override suspend fun insertData(list: List<InfoModelDb>) {
            coreDao.insertAll(list)
        }
    }
}