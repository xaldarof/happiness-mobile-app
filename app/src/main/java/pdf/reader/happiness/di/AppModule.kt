package pdf.reader.happiness.di

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.cache.*
import pdf.reader.happiness.data.cache.initilizers.HappyInitializer
import pdf.reader.happiness.data.cache.initilizers.LifeInitializer
import pdf.reader.happiness.data.cache.initilizers.LoveInitializer
import pdf.reader.happiness.data.cache.initilizers.SuccessInitializer
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.data.core.ToolsRepository
import pdf.reader.happiness.data.room.AppDatabase
import pdf.reader.happiness.data.settings_cache.CongratulationController
import pdf.reader.happiness.data.settings_cache.FontController
import pdf.reader.happiness.data.settings_cache.ThemeController
import pdf.reader.happiness.presentation.ReadingActivityPresenter
import pdf.reader.happiness.tools.CacheClear
import pdf.reader.happiness.vm.*

val cacheModule = module {
    single<RoomDatabase> { AppDatabase.getInstance(androidContext()) }
    factory<DataRepository> { DataRepository.Base(get(), get(), get(), get(), get()) }
    factory<ToolsRepository> { ToolsRepository.Base(get()) }

    factory<SuccessCacheDataSource> { SuccessCacheDataSource.Base(get()) }
    factory<HappyCacheDataSource> { HappyCacheDataSource.Base(get()) }
    factory<LifeCacheDataSource> { LifeCacheDataSource.Base(get()) }
    factory<LoveCacheDataSource> { LoveCacheDataSource.Base(get()) }
    factory<AllTypesCacheDataSource> { AllTypesCacheDataSource.Base(get()) }

    factory<SharedPreferences> { androidContext().getSharedPreferences("cache", MODE_PRIVATE) }
    factory<ThemeController> { ThemeController.Base(get()) }
    factory<FontController> { FontController.Base(get()) }
    factory<CongratulationController>{ CongratulationController.Base(get()) }
    factory<ReadingActivityPresenter> { ReadingActivityPresenter(get(), get()) }
    factory<CacheClear>{ CacheClear.Base(get(),get(),get(),get()) }
}

val initializers = module {
    factory { AppDatabase.getInstance(androidContext()).coreDaoProvider() }
    factory { AppDatabase.getInstance(androidContext()).toolsDaoProvider() }

    single<SuccessInitializer> { SuccessInitializer.Base(get()) }
    single<LifeInitializer> { LifeInitializer.Base(get()) }
    single<LoveInitializer> { LoveInitializer.Base(get()) }
    single<HappyInitializer> { HappyInitializer.Base(get()) }

}

val viewModels = module {
    single { SuccessViewModel(get()) }
    single { LifeViewModel(get()) }
    single { ReadingViewModel(get()) }
    single { HappyViewModel(get()) }
    single { LoveViewModel(get()) }
    single { SearchViewModel(get()) }
    single { FavoritesViewModel(get()) }

    single { MainActivityViewModel() }
    single { MainFragmentViewModel() }
}