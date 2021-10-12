package pdf.reader.happiness.di

import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.cache.data_source.*
import pdf.reader.happiness.data.cache.core.AchievementRepository
import pdf.reader.happiness.data.cache.core.ChaptersRepository
import pdf.reader.happiness.data.cache.core.CacheDataRepository
import pdf.reader.happiness.data.cache.core.ToolsRepository
import pdf.reader.happiness.core.AppDatabase
import pdf.reader.happiness.tools.AchievementUpdater


val cacheDataModule = module {
    single<RoomDatabase> { AppDatabase.getInstance(androidContext()) }
    factory<CacheDataRepository> { CacheDataRepository.Base(get(), get(), get(), get(), get()) }
    factory<ToolsRepository> { ToolsRepository.Base(get()) }
    factory<AchievementRepository> { AchievementRepository.Base(get()) }
    factory<ChaptersRepository> { ChaptersRepository.Base(get()) }


    factory<AchievementUpdater> { AchievementUpdater.Base(get(), get()) }

    factory<SuccessCacheDataSource> { SuccessCacheDataSource.Base(get()) }
    factory<HappyCacheDataSource> { HappyCacheDataSource.Base(get()) }
    factory<LifeCacheDataSource> { LifeCacheDataSource.Base(get()) }
    factory<LoveCacheDataSource> { LoveCacheDataSource.Base(get()) }
    factory<AllTypesCacheDataSource> { AllTypesCacheDataSource.Base(get()) }
    factory<AchievementDataSource> { AchievementDataSource.Base(get()) }
    factory<ChapterDataSource> { ChapterDataSource.Base(get()) }
    factory<MusicPathDataSource> { MusicPathDataSource.Base() }

}