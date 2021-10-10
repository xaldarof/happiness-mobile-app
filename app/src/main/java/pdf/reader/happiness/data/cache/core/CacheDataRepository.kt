package pdf.reader.happiness.data.cache.core

import kotlinx.coroutines.flow.*
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.data_source.*
import pdf.reader.happiness.data.cache.models.InfoModelDb

interface CacheDataRepository {

    suspend fun fetchSuccess(): Flow<List<InfoModel>>
    suspend fun fetchLife(): Flow<List<InfoModel>>
    suspend fun fetchHappy():Flow<List<InfoModel>>
    suspend fun fetchLove():Flow<List<InfoModel>>
    suspend fun fetchAllTypes():Flow<List<InfoModel>>

    suspend fun insertData(list: List<InfoModelDb>)

    class Base(private val successCacheDataSource: SuccessCacheDataSource,
               private val lifeCacheDataSource: LifeCacheDataSource,
               private val happyCacheDataSource: HappyCacheDataSource,
               private val loveCacheDataSource: LoveCacheDataSource,
               private val allTypesCacheDataSource: AllTypesCacheDataSource
    ) : CacheDataRepository {


        override suspend fun fetchSuccess(): Flow<List<InfoModel>> = successCacheDataSource.fetchSuccess()
        override suspend fun fetchLife(): Flow<List<InfoModel>> = lifeCacheDataSource.fetchLife()
        override suspend fun fetchHappy(): Flow<List<InfoModel>> = happyCacheDataSource.fetchHappy()
        override suspend fun fetchLove(): Flow<List<InfoModel>> = loveCacheDataSource.fetchLove()
        override suspend fun fetchAllTypes(): Flow<List<InfoModel>> = allTypesCacheDataSource.fetchAllTypes()

       override suspend fun insertData(list: List<InfoModelDb>) {
            allTypesCacheDataSource.insertData(list)
        }
    }
}
