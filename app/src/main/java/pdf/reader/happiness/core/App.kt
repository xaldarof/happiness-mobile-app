package pdf.reader.happiness.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import pdf.reader.happiness.data.cache.initilizers.HappyInitializer
import pdf.reader.happiness.data.cache.initilizers.LifeInitializer
import pdf.reader.happiness.data.cache.initilizers.LoveInitializer
import pdf.reader.happiness.data.cache.initilizers.SuccessInitializer
import pdf.reader.happiness.di.*

@KoinApiExtension
class App : Application(),KoinComponent{

    private val successInitializer: SuccessInitializer by inject()
    private val lifeInitializer:LifeInitializer by inject()
    private val loveInitializer:LoveInitializer by inject()
    private val happyInitializer:HappyInitializer by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(cacheDataModule, viewModels, initializers, tools, settingsCacheModule,
                presenters)
        }

        successInitializer.init()
        lifeInitializer.init()
        loveInitializer.init()
        happyInitializer.init()

    }
}