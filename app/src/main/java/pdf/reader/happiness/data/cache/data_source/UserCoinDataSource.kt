package pdf.reader.happiness.data.cache.data_source

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.dao.ToolsDao
import pdf.reader.happiness.data.cache.models.CoinModelDb

interface UserCoinDataSource {

    fun fetchUserCoinCountAsFlow():Flow<Int>
    fun fetchUserCoinCount():Int
    fun updateUserCoinCount()
    fun updateUserCoinCount(count:Int)
    fun payWithCoin(price:Int)

    fun initUserCoin()

    class Base(private val toolsDao: ToolsDao):UserCoinDataSource {

        override fun fetchUserCoinCountAsFlow(): Flow<Int> {
           return toolsDao.fetchUserCoinCountAsFlow()
    }

        override fun fetchUserCoinCount() = toolsDao.fetchUserCoinCount()

        override fun updateUserCoinCount() {
            toolsDao.updateUserCoin()
        }

        override fun updateUserCoinCount(count: Int) {
            toolsDao.updateUserCoin(count)
        }

        override fun payWithCoin(price: Int) {
            toolsDao.payWithCoin(price)
        }

        override fun initUserCoin() {
            toolsDao.initUserCoin(CoinModelDb("UCC", 0))
        }
    }
}