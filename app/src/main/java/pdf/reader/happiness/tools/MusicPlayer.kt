package pdf.reader.happiness.tools

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer

class MusicPlayer {

    private var mediaPlayer = MediaPlayer()

    fun play(context: Context, path: String) {
        val assetsFileDescriptor = context.assets.openFd(path)
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(
            assetsFileDescriptor.fileDescriptor,
            assetsFileDescriptor.startOffset,
            assetsFileDescriptor.length)
        mediaPlayer.setVolume(60f,60f)
        assetsFileDescriptor.close()
        mediaPlayer.prepare()
        mediaPlayer.isLooping = false
        mediaPlayer.start()

    }

    fun pause() {
        mediaPlayer.pause()
    }
}