package pdf.reader.happiness.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cache.models.AchievementModelDb

@Dao
interface AchievementDao {

    @Query("SELECT * FROM ach ORDER BY date DESC")
    fun fetchAchievements(): Flow<List<AchievementModelDb>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAchievement(achievementModelDb: AchievementModelDb)
}