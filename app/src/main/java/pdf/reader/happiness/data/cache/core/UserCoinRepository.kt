package pdf.reader.happiness.data.cache.core

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.data_source.UserCoinDataSource

interface UserCoinRepository {

    fun fetchUserCoinCountAsFlow(): Flow<Int>
    fun fetchUserCoinCount(): Int

    fun updateUserCoinCount()
    fun updateUserCoinCount(count:Int)

    fun payWithCoin(price:Int)

    fun initUserCoin()

    class Base(private val userCoinDataSource: UserCoinDataSource):UserCoinRepository {


        override fun fetchUserCoinCountAsFlow(): Flow<Int> = userCoinDataSource.fetchUserCoinCountAsFlow()

        override fun fetchUserCoinCount(): Int = userCoinDataSource.fetchUserCoinCount()


        override fun updateUserCoinCount() {
            userCoinDataSource.updateUserCoinCount()
        }

        override fun updateUserCoinCount(count: Int) {
            userCoinDataSource.updateUserCoinCount(count)
        }

        override fun payWithCoin(price: Int) {
            userCoinDataSource.payWithCoin(price)
        }

        override fun initUserCoin() {
            userCoinDataSource.initUserCoin()
        }
    }
}