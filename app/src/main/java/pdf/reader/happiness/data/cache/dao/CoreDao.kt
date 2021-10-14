package pdf.reader.happiness.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.models.InfoModelDb
import pdf.reader.happiness.data.cache.models.Type

@Dao
interface CoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(infoModelDb: InfoModelDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<InfoModelDb>)

    @Query("SELECT * FROM db")
    fun fetchAllTypes(): Flow<List<InfoModelDb>>


    @Query("SELECT * FROM db WHERE type =:type ORDER BY addedTime ASC")
    fun fetchInfoByType(type: Type): Flow<List<InfoModelDb>>

//    @Query("SELECT * FROM db WHERE type =:type ORDER BY addedTime ASC")
//    fun fetchLife(type: Type): Flow<List<InfoModelDb>>
//
//    @Query("SELECT * FROM db WHERE type=:type  ORDER BY addedTime ASC")
//    fun fetchHappy(type: Type): Flow<List<InfoModelDb>>
//
//    @Query("SELECT * FROM db WHERE type=:type  ORDER BY addedTime ASC")
//    fun fetchLove(type: Type): Flow<List<InfoModelDb>>


    @Query("SELECT * FROM db WHERE type =:type")
    fun fetchInfoCountByType(type: Type): List<InfoModelDb>

//    @Query("SELECT * FROM db WHERE type =:type")
//    fun fetchLifeCount(type: Type): List<InfoModelDb>
//
//    @Query("SELECT * FROM db WHERE type=:type")
//    fun fetchHappyCount(type: Type): List<InfoModelDb>
//
//    @Query("SELECT * FROM db WHERE type=:type")
//    fun fetchLoveCount(type: Type): List<InfoModelDb>


    @Query("SELECT * FROM db")
    fun fetchAll(): List<InfoModelDb>

    @Query("UPDATE chapters SET size=:size WHERE name=:name")
    fun updateChapterSize(name: String, size: Int)
}