package pdf.reader.happiness.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import pdf.reader.happiness.data.cache.initilizers.LifeInitializer
import pdf.reader.happiness.data.cache.initilizers.LoveInitializer
import pdf.reader.happiness.data.cache.initilizers.SuccessInitializer
import pdf.reader.happiness.di.cacheModule
import pdf.reader.happiness.di.initializers
import pdf.reader.happiness.di.tools
import pdf.reader.happiness.di.viewModels

@KoinApiExtension
class App : Application(),KoinComponent{

    private val successInitializer: SuccessInitializer by inject()
    private val lifeInitializer:LifeInitializer by inject()
    private val loveInitializer:LoveInitializer by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(cacheModule, viewModels, initializers, tools)
        }

        successInitializer.init()
        lifeInitializer.init()
        loveInitializer.init()

    }
}