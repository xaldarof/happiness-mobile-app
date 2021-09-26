package pdf.reader.happiness.data.cache

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.HappyModel
import pdf.reader.happiness.data.room.dao.HappyDao

interface HappyCacheDataSource {

    fun fetch(): Flow<List<HappyModel>>

    class Base(private val happyDao: HappyDao): HappyCacheDataSource {
        override fun fetch() = happyDao.fetchHappyData()

    }
}