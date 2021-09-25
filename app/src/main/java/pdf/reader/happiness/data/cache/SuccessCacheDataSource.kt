package pdf.reader.happiness.data.cache

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.InformationModel
import pdf.reader.happiness.data.room.MainDao

interface SuccessCacheDataSource {

    suspend fun fetchSuccess():Flow<List<InformationModel>>

    class Base(private val dao: MainDao) : SuccessCacheDataSource {
        override suspend fun fetchSuccess(): Flow<List<InformationModel>> = dao.fetchSuccessData()
    }
}