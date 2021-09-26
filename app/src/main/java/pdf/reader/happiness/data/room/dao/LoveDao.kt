package pdf.reader.happiness.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.LoveModel

@Dao
interface LoveDao {

    @Query("SELECT * FROM life")
    fun fetchLoveData(): Flow<List<LoveModel>>

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun insertLove(loveModel: LoveModel)

    @Query("UPDATE life SET favorite = :favorite WHERE body = :body")
    suspend fun updateFavoriteState(body:String,favorite:Boolean)

}