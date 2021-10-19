package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import pdf.reader.happiness.data.cache.core.UserCoinRepository
import pdf.reader.happiness.data.cloud.CloudDataRepository

class TokenViewModel(private val cloudDataRepository: CloudDataRepository,
                     private val coinRepository: UserCoinRepository): ViewModel() {

    suspend fun fetchTokenById(id:String) = cloudDataRepository.fetchTokenById(id)

    suspend fun createToken(tokenValue:Int)  = cloudDataRepository.createToken(tokenValue)

    suspend fun createTokenByUser(tokenValue:Int,tokenId: String)  =
        cloudDataRepository.createTokenByUser(tokenValue,tokenId)

    suspend fun removeToken(tokenId:String) = cloudDataRepository.removeToken(tokenId)

    fun fetchUserCoinCount() = coinRepository.fetchUserCoinCount()

    fun updateUserCoinCount(count: Int) = coinRepository.updateUserCoinCount(count)

    fun payWithCoin(count:Int) = coinRepository.payWithCoin(count)

}