package pdf.reader.happiness.di

import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.cache.SuccessCacheDataSource
import pdf.reader.happiness.data.cache.initilizers.SuccessInitializer
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.data.room.AppDatabase
import pdf.reader.happiness.data.room.MainDao
import pdf.reader.happiness.vm.SuccessViewModel

val cacheModule = module {
    single<RoomDatabase> { AppDatabase.getInstance(androidContext()) }
    factory<MainDao> { AppDatabase.getInstance(androidContext()).provideMainDao() }

    factory<DataRepository> { DataRepository.Base(get()) }

    single<SuccessInitializer> { SuccessInitializer.Base(get()) }
    factory <SuccessCacheDataSource> { SuccessCacheDataSource.Base(get()) }
}
val viewModels = module {
    single { SuccessViewModel(get()) }
}