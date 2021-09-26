package pdf.reader.happiness.data.core

import kotlinx.coroutines.flow.*
import pdf.reader.happiness.data.cache.LifeCacheDataSource
import pdf.reader.happiness.data.cache.SuccessCacheDataSource
import pdf.reader.happiness.data.models.InfoModel

interface DataRepository {

    suspend fun fetchSuccess(): Flow<List<InfoModel>>
    suspend fun fetchLife(): Flow<List<InfoModel>>

    suspend fun updateLifeFavorite(body:String,favorite:Boolean)
    suspend fun updateSuccessFavorite(body: String,favorite: Boolean)

    class Base(private val successCacheDataSource: SuccessCacheDataSource,
        private val lifeCacheDataSource: LifeCacheDataSource) : DataRepository {

        override suspend fun fetchSuccess(): Flow<List<InfoModel>> = successCacheDataSource.fetchSuccess()
        override suspend fun fetchLife(): Flow<List<InfoModel>> = lifeCacheDataSource.fetchLife()

        override suspend fun updateLifeFavorite(body: String, favorite: Boolean) {
            lifeCacheDataSource.updateFavoriteState(body,favorite)
        }

        override suspend fun updateSuccessFavorite(body: String, favorite: Boolean) {
            lifeCacheDataSource.updateFavoriteState(body, favorite)
        }
    }
}
