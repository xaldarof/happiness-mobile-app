package pdf.reader.happiness.core

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.google.firebase.firestore.auth.User
import pdf.reader.happiness.data.cache.models.*
import pdf.reader.happiness.data.cache.dao.*

@Database(
    entities = [InfoModelDb::class, AchievementModelDb::class,UserModelDb::class, ChapterModelDb::class, CoinModelDb::class, TokenModelDb::class],
    version = 28, exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun coreDaoProvider(): CoreDao
    abstract fun toolsDaoProvider(): ToolsDao
    abstract fun chaptersDaoProvider(): ChaptersDao
    abstract fun achievementDaoProvider(): AchievementDao
    abstract fun userDaoProvider(): UserDao

    companion object {
        private const val DATABASE_NAME = "DATABASE"

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}