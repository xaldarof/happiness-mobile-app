package pdf.reader.happiness.di

import androidx.room.RoomDatabase
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.cache.data_source.*
import pdf.reader.happiness.core.AppDatabase
import pdf.reader.happiness.data.cache.core.*
import pdf.reader.happiness.data.cache.settings_cache.AchievementUpdater
import pdf.reader.happiness.data.cloud.user.UserRepository


val cacheDataModule = module {
    single<RoomDatabase> { AppDatabase.getInstance(androidContext()) }
    factory<CacheDataRepository> { CacheDataRepository.Base(get(), get(), get(), get(), get()) }
    factory<ToolsRepository> { ToolsRepository.Base(get()) }
    factory<AchievementRepository> { AchievementRepository.Base(get()) }
    factory<ChaptersRepository> { ChaptersRepository.Base(get()) }


    factory<AchievementUpdater> { AchievementUpdater.Base(get(), get()) }
    factory<StatisticInfoProvider> { StatisticInfoProvider.Base(get(), get()) }

    factory<SuccessCacheDataSource> { SuccessCacheDataSource.Base(get()) }
    factory<HappyCacheDataSource> { HappyCacheDataSource.Base(get()) }
    factory<LifeCacheDataSource> { LifeCacheDataSource.Base(get()) }
    factory<LoveCacheDataSource> { LoveCacheDataSource.Base(get()) }
    factory<AllTypesCacheDataSource> { AllTypesCacheDataSource.Base(get()) }
    factory<AchievementDataSource> { AchievementDataSource.Base(get()) }
    factory<ChapterDataSource> { ChapterDataSource.Base(get()) }
    factory <MusicPathDataSource> { MusicPathDataSource.Base(FirebaseFirestore.getInstance()) }
    factory<StatisticDataSource> { StatisticDataSource.Base(get(), get()) }
    factory<TokenCacheDataSource> { TokenCacheDataSource.Base(get()) }

    factory<UserRepository> { UserRepository.Base(get()) }

}