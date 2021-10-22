package pdf.reader.happiness.tools

import android.media.MediaPlayer


class MusicPlayer {

    private val md: MediaPlayer?

    fun getMediaPlayer(): MediaPlayer? {
        if (md == null) {
            MusicPlayer()
        }
        return md
    }

    init {
        md = MediaPlayer()
    }
}
