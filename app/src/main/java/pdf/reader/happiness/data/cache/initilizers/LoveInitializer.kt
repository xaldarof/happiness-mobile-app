package pdf.reader.happiness.data.cache.initilizers

import pdf.reader.happiness.data.room.dao.CoreDao

interface LoveInitializer {

    fun init()

    class Base(private val coreDao: CoreDao): LoveInitializer {
        override fun init() {

        }
    }
}