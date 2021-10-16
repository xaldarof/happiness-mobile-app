package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.data.cache.core.UserCoinRepository
import pdf.reader.happiness.data.cloud.data_insert.CloudDataSendService
import pdf.reader.happiness.data.cloud.models.InfoCloudModel

class ShareViewModel(
    private val sendService: CloudDataSendService,
    private val coinRepository: UserCoinRepository
) : ViewModel() {


    fun sendDataToFirebase(infoCloudModel: InfoCloudModel) {
        CoroutineScope(Dispatchers.IO).launch {
            sendService.sendData(infoCloudModel)
            payWithCoin()
        }
    }

    fun fetchUserCoinCount() = coinRepository.fetchUserCoinCount()

    private fun payWithCoin() {
        coinRepository.payWithCoin(1)
    }
}