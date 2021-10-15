package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.data.cache.core.UserCoinRepository

class BonusFragmentViewModel(private val userCoinRepository: UserCoinRepository): ViewModel() {

    fun updateUserCoinCount() = userCoinRepository.updateUserCoinCount()

    fun fetchUserCoinCountAsFlow() = userCoinRepository.fetchUserCoinCountAsFlow().asLiveData()
}