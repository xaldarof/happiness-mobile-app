package pdf.reader.happiness.data.cache.data_source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.data.cache.dao.ToolsDao
import pdf.reader.happiness.data.cache.models.TokenModelDb

interface TokenCacheDataSource {

    suspend fun fetchTokenHistory(): Flow<List<TokenModel>>
    suspend fun addTokenToHistory(tokenModelDb: TokenModelDb)
    suspend fun deleteTokenHistory(tokenModelDb: TokenModelDb)

    class Base(private val toolsDao: ToolsDao) : TokenCacheDataSource {

        override suspend fun fetchTokenHistory(): Flow<List<TokenModel>> {
            return toolsDao.fetchTokenHistory().map { it.map { it.mapToToken() } }
        }

        override suspend fun addTokenToHistory(tokenModelDb: TokenModelDb) {
            toolsDao.addTokenHistory(tokenModelDb)
        }

        override suspend fun deleteTokenHistory(tokenModelDb: TokenModelDb) {
            toolsDao.deleteTokenHistory(tokenModelDb)
        }
    }
}