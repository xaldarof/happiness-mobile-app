package pdf.reader.happiness.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.CoreModel
import pdf.reader.happiness.data.models.Type

@Dao
interface CoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(coreModel: CoreModel)

    @Query("SELECT * FROM db")
    fun fetchAllTypes():Flow<List<CoreModel>>


    @Query("SELECT * FROM db WHERE type =:type")
    fun fetchSuccess(type: Type):Flow<List<CoreModel>>

    @Query("SELECT * FROM db WHERE type =:type")
    fun fetchLife(type: Type):Flow<List<CoreModel>>

    @Query("SELECT * FROM db WHERE type=:type")
    fun fetchHappy(type: Type):Flow<List<CoreModel>>

    @Query("SELECT * FROM db WHERE type=:type")
    fun fetchLove(type: Type):Flow<List<CoreModel>>
}