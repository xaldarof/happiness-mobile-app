package pdf.reader.happiness.di

import org.koin.dsl.module
import pdf.reader.happiness.vm.*


val viewModels = module {
    single { SuccessViewModel(get(),get(),get(),get()) }
    single { HappyViewModel(get(),get(),get(),get()) }
    single { LoveViewModel(get(),get(),get(),get()) }
    single { LifeViewModel(get(),get(),get(),get()) }

    single { ReadingViewModel(get()) }

    single { SearchViewModel(get()) }
    single { FavoritesViewModel(get()) }

    single { MainActivityViewModel(get(),get(),get()) }
    single { MainFragmentViewModel(get(),get()) }

    single { AchievementsViewModel(get()) }

    single { ImportingActivityViewModel(get(),get()) }
}