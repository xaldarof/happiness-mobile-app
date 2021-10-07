package pdf.reader.happiness.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.cache.initilizers.HappyInitializer
import pdf.reader.happiness.data.cache.initilizers.LifeInitializer
import pdf.reader.happiness.data.cache.initilizers.LoveInitializer
import pdf.reader.happiness.data.cache.initilizers.SuccessInitializer
import pdf.reader.happiness.core.AppDatabase



val initializers = module {
    factory { AppDatabase.getInstance(androidContext()).coreDaoProvider() }
    factory { AppDatabase.getInstance(androidContext()).toolsDaoProvider() }
    factory { AppDatabase.getInstance(androidContext()).chaptersDaoProvider() }
    factory { AppDatabase.getInstance(androidContext()).achievementDaoProvider() }

    single<SuccessInitializer> { SuccessInitializer.Base(get(),get()) }
    single<LifeInitializer> { LifeInitializer.Base(get(),get()) }
    single<LoveInitializer> { LoveInitializer.Base(get(),get()) }
    single<HappyInitializer> { HappyInitializer.Base(get(),get()) }

}