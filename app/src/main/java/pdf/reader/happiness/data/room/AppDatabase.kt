package pdf.reader.happiness.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pdf.reader.happiness.data.models.InformationModel

@Database(entities = [InformationModel::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun provideMainDao():MainDao

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