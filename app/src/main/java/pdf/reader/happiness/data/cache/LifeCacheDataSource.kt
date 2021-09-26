package pdf.reader.happiness.data.cache

import androidx.lifecycle.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.data.room.dao.LifeDao

interface LifeCacheDataSource {

    suspend fun fetchLife(): Flow<List<InfoModel>>
    suspend fun updateFavoriteState(body:String,favorite:Boolean)

    class Base(private val lifeDao: LifeDao): LifeCacheDataSource {
        override suspend fun fetchLife() : Flow<List<InfoModel>> {
            return lifeDao.fetchLifeData().map {
               it.map { it.mapToInfoModel() }
            }
        }

        override suspend fun updateFavoriteState(body: String, favorite: Boolean) {
            lifeDao.updateFavoriteState(body,favorite)
        }
    }
}