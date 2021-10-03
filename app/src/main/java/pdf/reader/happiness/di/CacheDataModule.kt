package pdf.reader.happiness.di

import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.cache.*
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.data.core.ToolsRepository
import pdf.reader.happiness.data.room.AppDatabase


val cacheDataModule = module {
    single<RoomDatabase> { AppDatabase.getInstance(androidContext()) }
    factory<DataRepository> { DataRepository.Base(get(), get(), get(), get(), get()) }
    factory<ToolsRepository> { ToolsRepository.Base(get()) }

    factory<SuccessCacheDataSource> { SuccessCacheDataSource.Base(get()) }
    factory<HappyCacheDataSource> { HappyCacheDataSource.Base(get()) }
    factory<LifeCacheDataSource> { LifeCacheDataSource.Base(get()) }
    factory<LoveCacheDataSource> { LoveCacheDataSource.Base(get()) }
    factory<AllTypesCacheDataSource> { AllTypesCacheDataSource.Base(get()) }

}