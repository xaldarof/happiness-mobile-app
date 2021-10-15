package pdf.reader.happiness.data.cache.core

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.data_source.UserCoinDataSource

interface UserCoinRepository {

    fun fetchUserCoinCountAsFlow(): Flow<Int>
    fun fetchUserCoinCount(): Int

    fun updateUserCoinCount()
    fun payWithCoin(price:Int)

    class Base(private val userCoinDataSource: UserCoinDataSource):UserCoinRepository {
        override fun fetchUserCoinCountAsFlow(): Flow<Int> = userCoinDataSource.fetchUserCoinCountAsFlow()

        override fun fetchUserCoinCount(): Int = userCoinDataSource.fetchUserCoinCount()

        override fun updateUserCoinCount() {
            userCoinDataSource.updateUserCoinCount()
        }

        override fun payWithCoin(price: Int) {
            userCoinDataSource.payWithCoin(price)
        }
    }
}