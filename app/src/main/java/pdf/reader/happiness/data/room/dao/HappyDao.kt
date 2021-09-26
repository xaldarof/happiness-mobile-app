package pdf.reader.happiness.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.HappyModel

@Dao
interface HappyDao {

    @Query("SELECT * FROM life")
    fun fetchHappyData(): Flow<List<HappyModel>>

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    fun insertHappy(happyModel: HappyModel)

}