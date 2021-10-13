package pdf.reader.happiness.data.cache.data_source

interface MusicPathDataSource {

    fun fetRandomPath(): String

    class Base : MusicPathDataSource {

        private val startPath = "music_med/"

        override fun fetRandomPath(): String {

            val paths = listOf("med1.mp3", "med2.mp3", "waterfall.mp3")

            return startPath.plus(paths[(paths.indices).random()])
        }
    }
}