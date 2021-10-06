package pdf.reader.happiness.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.AchievementModelDb
import pdf.reader.happiness.data.models.CoreModel


@Dao
interface ToolsDao {

    @Query("SELECT * FROM db WHERE title LIKE :query")
    fun fetchSearchResult(query:String): Flow<List<CoreModel>>


    @Query("SELECT * FROM db WHERE favorite==1")
    fun fetchFavorites(): Flow<List<CoreModel>>

    @Query("DELETE FROM db")
    fun delete()


    @Query("UPDATE db SET favorite = :favorite WHERE body = :body")
    suspend fun updateFavoriteState(body:String, favorite:Boolean)

    @Query("UPDATE db SET isOpened = :isOpened WHERE body = :body")
    suspend fun updateOpenedState(body:String, isOpened:Boolean)

    @Query("UPDATE db SET finished = :finished WHERE body = :body")
    suspend fun updateFinishedState(body:String, finished:Boolean)



    @Query("SELECT * FROM ach")
    fun fetchAchievements():Flow<List<AchievementModelDb>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAchievement(achievementModelDb: AchievementModelDb)
}