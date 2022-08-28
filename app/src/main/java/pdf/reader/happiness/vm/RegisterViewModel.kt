package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.UiState
import pdf.reader.happiness.data.cloud.auth.AuthRepository
import pdf.reader.happiness.data.cloud.user.UserRepository

/**
 * @Author: Temur
 * @Date: 28/08/2022
 */

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private var _loginState = Channel<UiState<String>>()
    val loginState = _loginState.receiveAsFlow()

    fun register(login: String, password: String) {
        viewModelScope.launch {
            authRepository.register(login, password, onSuccess = {
                viewModelScope.launch {
                    _loginState.send(UiState.Success())
                }
            }, onFail = {
                viewModelScope.launch {
                    _loginState.send(UiState.Fail(it))
                }
            })

        }
    }

}