package pdf.reader.happiness.data.cloud

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.core.CloudResult
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.data.cache.data_source.TokenCacheDataSource
import pdf.reader.happiness.data.cloud.data_source.InfoCloudDataSource
import pdf.reader.happiness.data.cloud.data_source.TokenCloudDataSource
import pdf.reader.happiness.data.cloud.models.InfoCloudModel
import pdf.reader.happiness.data.cloud.models.TokenCloudModel

interface CoinRepository {

    suspend fun fetchCloudData(): Flow<List<InfoCloudModel>>

    suspend fun fetchTokenById(id: String): CloudResult<TokenCloudModel>
    suspend fun fetchTokenHistory(): Flow<List<TokenModel>>
    suspend fun addTokenToHistory(tokenModel: TokenModel)
    suspend fun deleteTokenHistory(tokenModel: TokenModel)

    suspend fun createToken(tokenValue: Int)
    suspend fun createTokenByUser(tokenValue: Int, tokenId: String)
    suspend fun removeToken(tokenId: String): Boolean


    class Base(
        private val cloudDataSource: InfoCloudDataSource,
        private val tokenCloudDataSource: TokenCloudDataSource,
        private val tokenCacheDataSource: TokenCacheDataSource
    ) : CoinRepository {

        override suspend fun fetchCloudData(): Flow<List<InfoCloudModel>> {
            return cloudDataSource.fetchInfoAsFlow()
        }

        override suspend fun fetchTokenById(id: String): CloudResult<TokenCloudModel> {
            return tokenCloudDataSource.fetchTokenById(id)
        }

        override suspend fun fetchTokenHistory(): Flow<List<TokenModel>> {
            return tokenCacheDataSource.fetchTokenHistory()
        }

        override suspend fun addTokenToHistory(tokenModel: TokenModel) {
            tokenCacheDataSource.addTokenToHistory(tokenModel.mapToTokenDb())
        }

        override suspend fun createToken(tokenValue: Int) {
            tokenCloudDataSource.createToken(tokenValue)
        }

        override suspend fun createTokenByUser(tokenValue: Int, tokenId: String) {
            tokenCloudDataSource.createTokenByUser(tokenValue, tokenId)
        }

        override suspend fun removeToken(tokenId: String): Boolean {
            return tokenCloudDataSource.removeToken(tokenId)
        }

        override suspend fun deleteTokenHistory(tokenModel: TokenModel) {
            tokenCacheDataSource.deleteTokenHistory(tokenModel.mapToTokenDb())
        }
    }
}