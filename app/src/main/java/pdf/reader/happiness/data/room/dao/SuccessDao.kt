package pdf.reader.happiness.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.SuccessModel

@Dao
interface SuccessDao {

    @Query("SELECT * FROM success")
    fun fetchSuccessData():Flow<List<SuccessModel>>

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun insertSuccess(successModel: SuccessModel)


    @Query("UPDATE success SET favorite = :favorite WHERE body = :body")
    suspend fun updateSuccessState(body:String,favorite:Boolean)
}