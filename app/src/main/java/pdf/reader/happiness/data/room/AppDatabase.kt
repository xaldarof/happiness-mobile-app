package pdf.reader.happiness.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pdf.reader.happiness.data.models.HappyModel
import pdf.reader.happiness.data.models.LifeModel
import pdf.reader.happiness.data.models.LoveModel
import pdf.reader.happiness.data.models.SuccessModel
import pdf.reader.happiness.data.room.dao.HappyDao
import pdf.reader.happiness.data.room.dao.LifeDao
import pdf.reader.happiness.data.room.dao.LoveDao
import pdf.reader.happiness.data.room.dao.SuccessDao

@Database(entities = [SuccessModel::class,LoveModel::class,LifeModel::class,HappyModel::class],version = 3,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun successDaoProvider(): SuccessDao
    abstract fun lifeDaoProvider():LifeDao
    abstract fun loveDaoProvider():LoveDao
    abstract fun happyDaoProvider():HappyDao

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