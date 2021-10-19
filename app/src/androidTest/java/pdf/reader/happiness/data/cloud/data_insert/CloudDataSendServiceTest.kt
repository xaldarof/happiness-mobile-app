package pdf.reader.happiness.data.cloud.data_insert

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cloud.CoinRepository
import pdf.reader.happiness.data.cloud.models.InfoCloudModel

@KoinApiExtension
class CloudDataSendServiceTest : KoinComponent {

    private val cloudDataSendService: CloudDataSendService by inject()
    private val coinRepository:CoinRepository by inject()

    @Test
    fun check_is_data_sender_send_data_to_database() {
        val testModel =  InfoCloudModel("TEST", "TEST", type = Type.DEFAULT, dataType = Type.CLOUD)
        CoroutineScope(Dispatchers.Main).launch {
            cloudDataSendService.sendData(testModel)

            coinRepository.fetchCloudData().asLiveData().observeForever {
                assertTrue(it.contains(testModel))
            }
        }
    }
}