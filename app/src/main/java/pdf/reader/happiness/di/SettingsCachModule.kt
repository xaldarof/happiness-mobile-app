package pdf.reader.happiness.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.settings_cache.BadgeController
import pdf.reader.happiness.data.settings_cache.CongratulationController
import pdf.reader.happiness.data.settings_cache.FontController
import pdf.reader.happiness.data.settings_cache.ThemeController
import pdf.reader.happiness.presentation.ReadingActivityPresenter
import pdf.reader.happiness.tools.CacheClear


val settingsCacheModule = module {
    factory<SharedPreferences> { androidContext().getSharedPreferences("cache",
        Context.MODE_PRIVATE
    ) }
    factory<ThemeController> { ThemeController.Base(get()) }
    factory<FontController> { FontController.Base(get()) }
    factory<CongratulationController>{ CongratulationController.Base(get()) }
    factory<ReadingActivityPresenter> { ReadingActivityPresenter(get(), get()) }
    factory<CacheClear>{ CacheClear.Base(get(),get(),get(),get()) }

    factory<BadgeController>{ BadgeController.Base(get()) }
}