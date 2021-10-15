package pdf.reader.happiness.data.cache.core

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.data_source.UserCoinDataSource

interface UserCoinRepository {

    fun fetchUserCoinCount(): Flow<Int>
    fun updateUserCoinCount()

    class Base(private val userCoinDataSource: UserCoinDataSource):UserCoinRepository {
        override fun fetchUserCoinCount(): Flow<Int> {
           return userCoinDataSource.fetchUserCoinCount()
        }

        override fun updateUserCoinCount() {
            userCoinDataSource.updateUserCoinCount()
        }
    }
}