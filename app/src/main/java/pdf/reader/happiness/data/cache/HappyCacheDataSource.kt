package pdf.reader.happiness.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.data.models.Type
import pdf.reader.happiness.data.room.dao.CoreDao

interface HappyCacheDataSource {

    fun fetchHappy(): Flow<List<InfoModel>>

    class Base(private val coreDao: CoreDao) : HappyCacheDataSource {
        override fun fetchHappy(): Flow<List<InfoModel>> {
            return coreDao.fetchHappy(Type.HAPPY).map {
                it.map { it.mapToInfoModel() }
            }
        }
    }
}