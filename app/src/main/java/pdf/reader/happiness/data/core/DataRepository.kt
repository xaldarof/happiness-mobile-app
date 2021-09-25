package pdf.reader.happiness.data.core

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.SuccessCacheDataSource
import pdf.reader.happiness.data.models.InformationModel

interface DataRepository {

   suspend fun fetchSuccess():Flow<List<InformationModel>>

    class Base(private val successCacheDataSource: SuccessCacheDataSource) : DataRepository {

        override suspend fun fetchSuccess(): Flow<List<InformationModel>> = successCacheDataSource.fetchSuccess()

    }
}