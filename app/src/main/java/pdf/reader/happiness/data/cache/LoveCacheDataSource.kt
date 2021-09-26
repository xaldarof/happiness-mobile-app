package pdf.reader.happiness.data.cache

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.LoveModel
import pdf.reader.happiness.data.room.dao.LoveDao

interface LoveCacheDataSource {

    fun fetchLove():Flow<List<LoveModel>>

    class Base(private val loveDao: LoveDao): LoveCacheDataSource {
        override fun fetchLove() = loveDao.fetchLoveData()
    }
}