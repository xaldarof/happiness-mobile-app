package pdf.reader.happiness.data.cache.data_source

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.dao.ToolsDao
import pdf.reader.happiness.data.cache.models.CoinModelDb

interface UserCoinDataSource {

    fun fetchUserCoinCount():Flow<Int>
    fun updateUserCoinCount()

    class Base(private val toolsDao: ToolsDao):UserCoinDataSource {
        override fun fetchUserCoinCount(): Flow<Int> {
            return toolsDao.fetchUserCoinCount()
        }

        override fun updateUserCoinCount() {
            toolsDao.initUserCoin(CoinModelDb("UCC",0))
            toolsDao.updateUserCoin()
        }
    }
}