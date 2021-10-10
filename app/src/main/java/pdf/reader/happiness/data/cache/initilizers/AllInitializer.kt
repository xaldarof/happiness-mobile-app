package pdf.reader.happiness.data.cache.initilizers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllInitializer(
    private val happyInitializer: HappyInitializer,
    private val successInitializer: SuccessInitializer,
    private val lifeInitializer: LifeInitializer,
    private val loveInitializer: LoveInitializer
) {

    fun invokeAll() {
       CoroutineScope(Dispatchers.IO).launch {
           happyInitializer.init()
           successInitializer.init()
           lifeInitializer.init()
           loveInitializer.init()
       }
    }
}