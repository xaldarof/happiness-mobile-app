package pdf.reader.happiness.data.cache.initilizers

import pdf.reader.happiness.data.room.dao.LoveDao

interface LoveInitializer {

    fun init()

    class Base(private val loveDao: LoveDao): LoveInitializer {
        override fun init() {

        }
    }
}