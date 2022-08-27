package pdf.reader.happiness.data.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */

@Entity(tableName = "user")
data class UserModelDb(
    @PrimaryKey
    val login: String,
    val password: String,
    val balance: Int,
)