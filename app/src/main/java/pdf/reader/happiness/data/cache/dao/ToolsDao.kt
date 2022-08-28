package pdf.reader.happiness.data.cache.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.data.cache.models.CoinModelDb
import pdf.reader.happiness.data.cache.models.InfoModelDb
import pdf.reader.happiness.data.cache.models.TokenModelDb


@Dao
interface ToolsDao {

    @Query("SELECT * FROM db WHERE title LIKE :query")
    fun fetchSearchResult(query: String): Flow<List<InfoModelDb>>

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
    suspend fun updateFavoriteState(body: String, favorite: Boolean)

    @Query("UPDATE db SET isOpened = :isOpened WHERE body = :body")
    suspend fun updateOpenedState(body: String, isOpened: Boolean)

    @Query("UPDATE db SET finished = :finished WHERE body = :body")
    suspend fun updateFinishedState(body: String, finished: Boolean)

    @Query("UPDATE db SET readTimeSeconds = readTimeSeconds+3 WHERE body = :body")
    suspend fun updateReadTimeSeconds(body: String)

    @Query("UPDATE db SET enterCount = enterCount+1 WHERE body = :body")
    suspend fun updateEnterCount(body: String)


    //USER COIN

    @Query("UPDATE coins SET coinCount=coinCount+2 WHERE name='UCC'")
    fun updateUserCoin()

    @Query("UPDATE coins SET coinCount=coinCount+:count WHERE name='UCC'")
    fun updateUserCoin(count: Int)


    @Query("UPDATE coins SET coinCount=0 WHERE name='UCC'")
    fun clearUserCoin()

    @Query("UPDATE coins SET coinCount=coinCount-:money WHERE name='UCC'")
    fun payWithCoin(money: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun initUserCoin(coinModelDb: CoinModelDb)


    @Query("SELECT coinCount FROM coins WHERE name='UCC'")
    fun fetchUserCoinCountAsFlow(): Flow<Int>

    @Query("SELECT coinCount FROM coins WHERE name='UCC'")
    fun fetchUserCoinCount(): Int


    //Tokens

    @Query("SELECT * FROM token_history ORDER BY tokenDate DESC")
    fun fetchTokenHistory(): Flow<List<TokenModelDb>>

    @Delete
    fun deleteTokenHistory(tokenModelDb: TokenModelDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTokenHistory(tokenModelDb: TokenModelDb)

}