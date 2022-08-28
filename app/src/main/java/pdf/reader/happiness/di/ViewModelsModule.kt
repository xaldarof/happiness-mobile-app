package pdf.reader.happiness.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import pdf.reader.happiness.vm.*


val viewModels = module {
    factory { SuccessViewModel(get(), get(), get(), get()) }
    factory { HappyViewModel(get(), get(), get(), get()) }
    factory { LoveViewModel(get(), get(), get(), get()) }
    factory { LifeViewModel(get(), get(), get(), get()) }

    factory { ReadingViewModel(get()) }

    factory { SearchViewModel(get()) }
    factory { FavoritesViewModel(get()) }

    factory { MainActivityViewModel(get(), get(), get(),get(),get()) }
    factory { MainFragmentViewModel(get(), get()) }

    factory { AchievementsViewModel(get()) }

    factory { ImportingActivityViewModel(get(), get(), get(), get(), get()) }

    factory { MeditationFragmentViewModel(get(), get(), androidApplication()) }

    factory { StatisticViewModel(get()) }

    factory { BonusFragmentViewModel(get()) }

    factory { ShareViewModel(get(), get()) }

    factory { TokenViewModel(get(), get()) }

    factory { LoginViewModel(get(),get()) }
    factory { RegisterViewModel(get(),get()) }
}