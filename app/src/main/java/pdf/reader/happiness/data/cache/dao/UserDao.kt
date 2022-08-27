package pdf.reader.happiness.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.models.UserModelDb

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun fetUser(): Flow<UserModelDb?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userModelDb: UserModelDb)
}