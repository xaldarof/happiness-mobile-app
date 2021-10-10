package pdf.reader.happiness.data.cloud

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class CloudDataRepositoryTest : KoinComponent {

    private val repository: CloudDataRepository by inject()

    @Test
    fun check_is_cloud_data_works() {
        CoroutineScope(Dispatchers.Main).launch {
            repository.fetchCloudData().asLiveData().observeForever {
                assertTrue(it.size > 1)
            }
        }
    }
}