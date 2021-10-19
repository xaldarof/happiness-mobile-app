package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.data.cache.core.UserCoinRepository
import pdf.reader.happiness.data.cloud.CoinRepository

class TokenViewModel(
    private val cloudDataRepository: CoinRepository,
    private val coinRepository: UserCoinRepository
) : ViewModel() {

    suspend fun fetchTokenById(id: String) = cloudDataRepository.fetchTokenById(id)

    suspend fun fetchTokenHistory() = cloudDataRepository.fetchTokenHistory().asLiveData()
    suspend fun addTokenHistory(tokenModel: TokenModel) =
        cloudDataRepository.addTokenToHistory(tokenModel)

    suspend fun deleteTokenHistory(tokenModel: TokenModel) = cloudDataRepository.deleteTokenHistory(tokenModel)

    suspend fun createToken(tokenValue: Int) = cloudDataRepository.createToken(tokenValue)

    suspend fun createTokenByUser(tokenValue: Int, tokenId: String) =
        cloudDataRepository.createTokenByUser(tokenValue, tokenId)

    suspend fun removeToken(tokenId: String) = cloudDataRepository.removeToken(tokenId)

    fun fetchUserCoinCount() = coinRepository.fetchUserCoinCount()

    fun updateUserCoinCount(count: Int) = coinRepository.updateUserCoinCount(count)

    fun payWithCoin(count: Int) = coinRepository.payWithCoin(count)

}