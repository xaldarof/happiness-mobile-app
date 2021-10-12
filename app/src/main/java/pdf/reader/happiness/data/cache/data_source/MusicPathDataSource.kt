package pdf.reader.happiness.data.cache.data_source

interface MusicPathDataSource {

    fun fetRandomPath(): String

    class Base : MusicPathDataSource {

        private val startPath = "music_med/"

        override fun fetRandomPath(): String {

            val paths = listOf("meditation1.mp3", "meditation8d.mp3")

            return startPath.plus(paths[(paths.indices).random()])
        }
    }
}