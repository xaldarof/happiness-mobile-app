package pdf.reader.happiness.data.cache.initilizers

import pdf.reader.happiness.data.room.dao.HappyDao

interface HappyInitializer {

    fun init()

    class Base (private val happyDao: HappyDao): HappyInitializer {
        override fun init() {

        }
    }
}