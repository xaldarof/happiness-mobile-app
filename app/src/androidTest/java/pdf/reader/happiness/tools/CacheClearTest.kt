package pdf.reader.happiness.tools

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.settings_cache.CacheClear

@KoinApiExtension
class CacheClearTest : KoinComponent {

    private val cacheClear: CacheClear by inject()
    private val cacheDataRepository: CacheDataRepository by inject()

    @Test
    fun check_is_all_data_cleared() {
        cacheClear.clear()

        CoroutineScope(Dispatchers.Main).launch {
            cacheDataRepository.fetchAllTypes().asLiveData().observeForever {
                assertTrue(it.isEmpty())
            }
        }
    }
}