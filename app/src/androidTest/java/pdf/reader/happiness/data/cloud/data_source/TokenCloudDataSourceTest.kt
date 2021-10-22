package pdf.reader.happiness.data.cloud.data_source

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.core.TokenCloudResult
import pdf.reader.happiness.data.cloud.data_insert.TokenIdGenerator

@KoinApiExtension
class TokenCloudDataSourceTest : KoinComponent {

    private val tokenCloudDataSource: TokenCloudDataSource by inject()

    @Test
    fun check_is_returns_true_data_when_enter_true_token_id() = runBlocking {
        val tokenId = TokenIdGenerator().getGeneratedId()

        tokenCloudDataSource.createTokenByUser(1, tokenId)
        val res = tokenCloudDataSource.fetchTokenById(tokenId)

        when(res){
            is TokenCloudResult.Success ->{
                assertTrue(res.data.tokenId == tokenId)
            }
        }
    }
}