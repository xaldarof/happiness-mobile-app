package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.data.cloud.CoinRepository
import pdf.reader.happiness.data.cloud.user.UserRepository

class TokenViewModel(
    private val cloudDataRepository: CoinRepository,
    private val coinRepository: UserRepository
) : ViewModel() {

    suspend fun fetchTokenById(id: String) = cloudDataRepository.fetchTokenById(id)

    suspend fun fetchTokenHistory() = cloudDataRepository.fetchTokenHistory().asLiveData()
    suspend fun addTokenHistory(tokenModel: TokenModel) =
        cloudDataRepository.addTokenToHistory(tokenModel)

    suspend fun deleteTokenHistory(tokenModel: TokenModel) =
        cloudDataRepository.deleteTokenHistory(tokenModel)

    suspend fun createToken(tokenValue: Int) = cloudDataRepository.createToken(tokenValue)

    suspend fun createTokenByUser(tokenValue: Int, tokenId: String) =
        cloudDataRepository.createTokenByUser(tokenValue, tokenId)

    suspend fun removeToken(tokenId: String) = cloudDataRepository.removeToken(tokenId)

    fun fetchUserCoinCount() = coinRepository.fetchUserBalance()

    fun updateUserCoinCount(
        count: Int, onSuccess: () -> Unit,
        onFail: (String) -> Unit
    ) {
        coinRepository.updateBalance(count, onSuccess = {
            onSuccess()
        }, onFail = {
            onFail(it)
        })
    }

    fun payWithCoin(count: Int,   onSuccess: () -> Unit,
                    onFail: (String) -> Unit) {
        coinRepository.invokePayment(count, onSuccess = onSuccess,onFail = onFail)
    }

}