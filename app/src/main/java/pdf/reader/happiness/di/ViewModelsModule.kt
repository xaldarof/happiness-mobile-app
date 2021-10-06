package pdf.reader.happiness.di

import org.koin.dsl.module
import pdf.reader.happiness.vm.*


val viewModels = module {
    single { SuccessViewModel(get()) }
    single { LifeViewModel(get()) }
    single { ReadingViewModel(get()) }
    single { HappyViewModel(get()) }
    single { LoveViewModel(get()) }
    single { SearchViewModel(get()) }
    single { FavoritesViewModel(get()) }

    single { MainActivityViewModel() }
    single { MainFragmentViewModel(get()) }

    single { AchievementsViewModel(get()) }
}