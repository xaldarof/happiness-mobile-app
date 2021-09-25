package pdf.reader.happiness.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.InformationModel

@Dao
interface MainDao {

    @Query("SELECT * FROM all_data")
    fun fetchSuccessData():Flow<List<InformationModel>>

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    fun insertSuccess(informationModel: InformationModel)

}