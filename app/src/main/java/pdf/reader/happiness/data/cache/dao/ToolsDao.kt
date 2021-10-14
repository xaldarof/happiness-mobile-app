package pdf.reader.happiness.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.models.InfoModelDb


@Dao
interface ToolsDao {

    @Query("SELECT * FROM db WHERE title LIKE :query")
    fun fetchSearchResult(query:String): Flow<List<InfoModelDb>>

    @Query("SELECT * FROM db")
    fun fetchAll(): List<InfoModelDb>


    @Query("SELECT * FROM db WHERE favorite==1")
    fun fetchFavorites(): Flow<List<InfoModelDb>>

    @Query("DELETE FROM db")
    fun deleteTypes()

    @Query("DELETE FROM chapters")
    fun deleteChapters()

    @Query("DELETE FROM ach")
    fun deleteAchievement()


    @Query("UPDATE db SET favorite = :favorite WHERE body = :body")
    suspend fun updateFavoriteState(body:String, favorite:Boolean)

    @Query("UPDATE db SET isOpened = :isOpened WHERE body = :body")
    suspend fun updateOpenedState(body:String, isOpened:Boolean)

    @Query("UPDATE db SET finished = :finished WHERE body = :body")
    suspend fun updateFinishedState(body:String, finished:Boolean)

    @Query("UPDATE db SET readTimeSeconds = readTimeSeconds+3 WHERE body = :body")
    suspend fun updateReadTimeSeconds(body:String)

    @Query("UPDATE db SET enterCount = enterCount+1 WHERE body = :body")
    suspend fun updateEnterCount(body:String)

}