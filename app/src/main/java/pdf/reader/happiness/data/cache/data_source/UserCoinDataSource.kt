package pdf.reader.happiness.data.cache.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.dao.ToolsDao
import pdf.reader.happiness.data.cache.models.CoinModelDb

interface UserCoinDataSource {

    fun fetchUserCoinCountAsFlow():Flow<Int>
    fun fetchUserCoinCount():Int
    fun updateUserCoinCount()
    fun payWithCoin(price:Int)

    class Base(private val toolsDao: ToolsDao):UserCoinDataSource {

        override fun fetchUserCoinCountAsFlow(): Flow<Int> = toolsDao.fetchUserCoinCountAsFlow()

        override fun fetchUserCoinCount() = toolsDao.fetchUserCoinCount()

        override fun updateUserCoinCount() {
            toolsDao.initUserCoin(CoinModelDb("UCC",0))
            toolsDao.updateUserCoin()
        }

        override fun payWithCoin(price: Int) {
            toolsDao.payWithCoin(price)
        }
    }
}