package pdf.reader.happiness.data.cache.data_source

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.data.cache.models.TokenModelDb
import pdf.reader.happiness.data.cloud.data_insert.TokenIdGenerator

@KoinApiExtension
class TokenCacheDataSourceTest : KoinComponent {

    private val tokenCacheDataSource: TokenCacheDataSource by inject()
    private val testModel1 = TokenModelDb(123, (System.currentTimeMillis() / 1000).toString(), TokenIdGenerator().getGeneratedId())
    private val testModel2 = TokenModelDb(123, (System.currentTimeMillis() / 1000).toString(), TokenIdGenerator().getGeneratedId())

    @Test
    fun check_is_return_token_transaction_history() {
        CoroutineScope(Dispatchers.Main).launch {
            tokenCacheDataSource.fetchTokenHistory().asLiveData().observeForever {
                assertTrue(it.isNotEmpty())
                assertFalse(it.isEmpty())
            }
        }
    }

    @Test
    fun check_is_deletes_token_transaction_history(): Unit = runBlocking {
        tokenCacheDataSource.addTokenToHistory(testModel1)

        tokenCacheDataSource.deleteTokenHistory(testModel1)

        CoroutineScope(Dispatchers.Main).launch {
            tokenCacheDataSource.fetchTokenHistory().asLiveData().observeForever {
                assertTrue(!it.contains(testModel1.mapToToken()))
                assertFalse(it.contains(testModel1.mapToToken()))
            }
        }
    }

    @Test
    fun check_is_create_token_transaction_history(): Unit = runBlocking {

        tokenCacheDataSource.addTokenToHistory(testModel2)

        CoroutineScope(Dispatchers.Main).launch {
            tokenCacheDataSource.fetchTokenHistory().asLiveData().observeForever {
                assertTrue(it.contains(testModel2.mapToToken()))
                assertFalse(!it.contains(testModel2.mapToToken()))
            }
        }
    }
}