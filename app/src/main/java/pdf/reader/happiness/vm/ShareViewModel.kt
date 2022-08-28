package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.data.cloud.data_insert.CloudDataSendService
import pdf.reader.happiness.data.cloud.models.InfoCloudModel
import pdf.reader.happiness.data.cloud.user.UserRepository

class ShareViewModel(
    private val sendService: CloudDataSendService,
    private val coinRepository: UserRepository
) : ViewModel() {


    fun sendDataToFirebase(
        infoCloudModel: InfoCloudModel, onSuccess: () -> Unit,
        onFail: (String) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            sendService.sendData(infoCloudModel, onSuccess = {
                payWithCoin(onSuccess = {
                    onSuccess()
                }, onFail = {
                    onFail(it)
                })
            }, onFail = {

            })
        }
    }

    fun fetchUserCoinCount() = coinRepository.fetchUserBalance()

    private fun payWithCoin(
        onSuccess: () -> Unit,
        onFail: (String) -> Unit
    ) {
        coinRepository.invokePayment(1, onFail = {
            onFail(it)
        }, onSuccess = {
            onSuccess()
        })
    }
}