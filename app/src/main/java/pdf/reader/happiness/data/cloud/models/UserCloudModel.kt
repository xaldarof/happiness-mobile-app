package pdf.reader.happiness.data.cloud.models

import pdf.reader.happiness.data.cache.models.UserModelDb
import java.io.Serializable

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */

data class UserCloudModel(
    val login: String = "",
    val password: String = "",
    val balance: Int = -1,
) {
    fun mapToCache(): UserModelDb {
        return UserModelDb(login, password, balance)
    }

//test
}
