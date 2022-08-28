package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import pdf.reader.happiness.data.cloud.user.UserRepository

class BonusFragmentViewModel(private val userCoinRepository: UserRepository) : ViewModel() {

    fun updateUserCoinCount(
        onSuccess: () -> Unit,
        onFail: (String) -> Unit
    ) {
        userCoinRepository.updateBalance(onFail = onFail, onSuccess = onSuccess, count = 2)
    }

    fun fetchUserCoinCountAsFlow() = userCoinRepository.fetchUserBalanceAsFlow()
    fun fetchUserCoinCount() = userCoinRepository.fetchUserBalance()

}