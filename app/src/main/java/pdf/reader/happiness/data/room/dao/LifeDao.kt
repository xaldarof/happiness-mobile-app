package pdf.reader.happiness.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.LifeModel

@Dao
interface LifeDao {

    @Query("SELECT * FROM life")
    fun fetchLifeData(): Flow<List<LifeModel>>

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun insertLife(lifeModel: LifeModel)

    @Query("UPDATE life SET favorite = :favorite WHERE body = :body")
    suspend fun updateFavoriteState(body:String,favorite:Boolean)
}