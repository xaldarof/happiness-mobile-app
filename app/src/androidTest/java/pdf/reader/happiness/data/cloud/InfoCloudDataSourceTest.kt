package pdf.reader.happiness.data.cloud

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.data.cloud.data_source.InfoCloudDataSource

@KoinApiExtension
class InfoCloudDataSourceTest : KoinComponent {

    private val infoCloudDataSource: InfoCloudDataSource by inject()

    @Test
    fun check_is_cloud_data_source_returns_data() {
        CoroutineScope(Dispatchers.Main).launch {
            infoCloudDataSource.fetchInfoAsFlow().asLiveData().observeForever {
                assertTrue(it.size>1)
            }
        }
    }
}