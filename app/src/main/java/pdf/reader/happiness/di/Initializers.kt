package pdf.reader.happiness.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.core.AppDatabase
import pdf.reader.happiness.data.cache.initilizers.*


val initializers = module {
    factory { AppDatabase.getInstance(androidContext()).coreDaoProvider() }
    factory { AppDatabase.getInstance(androidContext()).toolsDaoProvider() }
    factory { AppDatabase.getInstance(androidContext()).chaptersDaoProvider() }
    factory { AppDatabase.getInstance(androidContext()).achievementDaoProvider() }

    factory<SuccessInitializer> { SuccessInitializer.Base(get(), get()) }
    factory<LifeInitializer> { LifeInitializer.Base(get(), get()) }
    factory<LoveInitializer> { LoveInitializer.Base(get(), get()) }
    factory<HappyInitializer> { HappyInitializer.Base(get(), get()) }

    factory { AllInitializer(get(),get(),get(),get()) }

}