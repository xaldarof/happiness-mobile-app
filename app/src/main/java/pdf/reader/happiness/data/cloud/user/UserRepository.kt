package pdf.reader.happiness.data.cloud.user

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.dao.UserDao
import pdf.reader.happiness.data.cache.models.UserModelDb

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */

interface UserRepository {

    fun fetchUser(): Flow<UserModelDb?>

    class Base(private val userDao: UserDao) : UserRepository {
        override fun fetchUser(): Flow<UserModelDb?> {
            return  userDao.fetUser()
        }
    }
}