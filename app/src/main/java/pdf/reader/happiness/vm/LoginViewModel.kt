package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    private var _loginState = MutableStateFlow<UiState<String>>(UiState.Nothing())
    val loginState = _loginState.asStateFlow()


    var isAuthorized = false
        private set

    fun login(login: String, password: String) {
        viewModelScope.launch {
            authRepository.login(login, password, onSuccess = {
                _loginState.value = UiState.Success()
            }, onFail = {
                _loginState.value = UiState.Fail()
            }, onNotExists = {
                _loginState.value = UiState.Fail()
            })
        }
    }

    init {
        checkAppState()
    }

    private fun checkAppState() {
        viewModelScope.launch {
            userRepository.fetchUser().collectLatest {
                isAuthorized = it != null
            }
        }
    }

}