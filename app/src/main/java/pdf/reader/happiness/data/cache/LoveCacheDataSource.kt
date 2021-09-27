package pdf.reader.happiness.data.cache

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.data.room.dao.CoreDao

interface LoveCacheDataSource {

    fun fetchLove():Flow<List<InfoModel>>

    class Base(private val coreDao: CoreDao) {
    }
}