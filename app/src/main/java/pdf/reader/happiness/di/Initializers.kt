package pdf.reader.happiness.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.cache.initilizers.HappyInitializer
import pdf.reader.happiness.data.cache.initilizers.LifeInitializer
import pdf.reader.happiness.data.cache.initilizers.LoveInitializer
import pdf.reader.happiness.data.cache.initilizers.SuccessInitializer
import pdf.reader.happiness.data.room.AppDatabase



val initializers = module {
    factory { AppDatabase.getInstance(androidContext()).coreDaoProvider() }
    factory { AppDatabase.getInstance(androidContext()).toolsDaoProvider() }

    single<SuccessInitializer> { SuccessInitializer.Base(get()) }
    single<LifeInitializer> { LifeInitializer.Base(get()) }
    single<LoveInitializer> { LoveInitializer.Base(get()) }
    single<HappyInitializer> { HappyInitializer.Base(get()) }

}