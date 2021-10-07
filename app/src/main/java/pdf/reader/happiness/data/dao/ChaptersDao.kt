package pdf.reader.happiness.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.models.ChapterModelDb


@Dao
interface ChaptersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertChapter(chapterModelDb: ChapterModelDb)

    @Query("SELECT * FROM chapters")
    fun fetchChapters(): Flow<List<ChapterModelDb>>

    @Query("UPDATE chapters SET isFinished=:isFinished WHERE name=:chapterName")
    fun updateChapterFinishedState(isFinished:Boolean,chapterName:String)

    @Query("UPDATE chapters SET progress=:progress WHERE name=:chapterName")
    fun updateChapterProgress(progress:Float,chapterName:String)

}