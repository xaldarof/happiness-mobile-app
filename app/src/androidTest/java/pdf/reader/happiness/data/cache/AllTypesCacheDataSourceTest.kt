package pdf.reader.happiness.data.cache

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.data.dao.CoreDao

@KoinApiExtension
class AllTypesCacheDataSourceTest: KoinComponent {

    private val dao:CoreDao by inject()

    /**
     *  checked when actual size of data was 105
     */

    @Test
    fun check_is_db_returns_data() {
        var size = 0
        CoroutineScope(Dispatchers.Main).launch {
            dao.fetchAllTypes().asLiveData().observeForever {
                size = it.size
                assertEquals(105,size)
            }
        }
    }
}