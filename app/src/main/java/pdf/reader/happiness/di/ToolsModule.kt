package pdf.reader.happiness.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.core.AchievementRepository
import pdf.reader.happiness.tools.AssetReader

val tools = module {
    factory<AssetReader> { AssetReader.Base(androidContext()) }
}
