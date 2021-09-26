package pdf.reader.happiness.di

import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.core.App
import pdf.reader.happiness.data.cache.HappyCacheDataSource
import pdf.reader.happiness.data.cache.LifeCacheDataSource
import pdf.reader.happiness.data.cache.LoveCacheDataSource
import pdf.reader.happiness.data.cache.SuccessCacheDataSource
import pdf.reader.happiness.data.cache.initilizers.HappyInitializer
import pdf.reader.happiness.data.cache.initilizers.LifeInitializer
import pdf.reader.happiness.data.cache.initilizers.LoveInitializer
import pdf.reader.happiness.data.cache.initilizers.SuccessInitializer
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.data.room.AppDatabase
import pdf.reader.happiness.data.room.dao.LifeDao
import pdf.reader.happiness.data.room.dao.LoveDao
import pdf.reader.happiness.data.room.dao.SuccessDao
import pdf.reader.happiness.vm.LifeViewModel
import pdf.reader.happiness.vm.ReadingViewModel
import pdf.reader.happiness.vm.SuccessViewModel

val cacheModule = module {
    single<RoomDatabase> { AppDatabase.getInstance(androidContext()) }

    factory<DataRepository> { DataRepository.Base(get(), get()) }

    factory<SuccessCacheDataSource> { SuccessCacheDataSource.Base(get()) }
    factory<LoveCacheDataSource> { LoveCacheDataSource.Base(get()) }
    factory<HappyCacheDataSource> { HappyCacheDataSource.Base(get()) }
    factory<LifeCacheDataSource> { LifeCacheDataSource.Base(get()) }
}

val initializers = module {
    factory<SuccessDao> { AppDatabase.getInstance(androidContext()).successDaoProvider() }
    factory<LifeDao> { AppDatabase.getInstance(androidContext()).lifeDaoProvider() }
    factory<LoveDao> { AppDatabase.getInstance(androidContext()).loveDaoProvider() }
    factory { AppDatabase.getInstance(androidContext()).happyDaoProvider() }

    single<SuccessInitializer> { SuccessInitializer.Base(get()) }
    single<LifeInitializer> { LifeInitializer.Base(get()) }
    single<LoveInitializer> { LoveInitializer.Base(get()) }
    single<HappyInitializer> { HappyInitializer.Base(get()) }

}
val viewModels = module {
    single { SuccessViewModel(get()) }
    single { LifeViewModel(get()) }
    single { ReadingViewModel(get()) }
}