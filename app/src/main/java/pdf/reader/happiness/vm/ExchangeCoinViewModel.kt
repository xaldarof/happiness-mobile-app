package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.UiState
import pdf.reader.happiness.data.cloud.user.UserRepository

/**
 * @Author: Temur
 * @Date: 28/08/2022
 */

class ExchangeCoinViewModel(private val userRepository: UserRepository) : ViewModel() {


    fun sendTo(
        login: String, count: Int, onSuccess: () -> Unit,
        onFail: (String) -> Unit
    ) {
        viewModelScope.launch {
            userRepository.sendTo(login, count, onSuccess, onFail)
        }
    }


}