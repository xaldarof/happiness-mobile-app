package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.UiState
import pdf.reader.happiness.data.cloud.auth.AuthRepository
import pdf.reader.happiness.data.cloud.user.UserRepository

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private var _loginState = Channel<UiState<String>>()
    val loginState = _loginState.receiveAsFlow()


    var isAuthorized = false
        private set

    fun login(login: String, password: String) {
        viewModelScope.launch {
            authRepository.login(login, password, onSuccess = {
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

    init {
        checkAppState()
    }

    private fun checkAppState() {
        viewModelScope.launch {
            userRepository.fetchUserAsFlow().collectLatest {
                isAuthorized = it != null
            }
        }
    }

}