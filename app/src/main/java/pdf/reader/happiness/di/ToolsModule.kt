package pdf.reader.happiness.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pdf.reader.happiness.data.cache.settings_cache.AssetReader
import pdf.reader.happiness.data.cloud.data_insert.TokenIdGenerator
import pdf.reader.happiness.tools.MusicPlayer
import pdf.reader.happiness.tools.PercentCalculator

val tools = module {
    factory<AssetReader> { AssetReader.Base(androidContext()) }
    factory<PercentCalculator> { PercentCalculator.Base() }
    single<MusicPlayer> { MusicPlayer() }
    factory<TokenIdGenerator> { TokenIdGenerator() }

}
