package pdf.reader.happiness.tools

import android.content.Context
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Vibrator
import androidx.test.core.app.ApplicationProvider
import nl.dionsegijn.konfetti.KonfettiView
import pdf.reader.happiness.R

class CongratulationView(private val confetti: KonfettiView) {

    fun show() {

        vibrate()
        val context = confetti.context
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 50, 0)
        val mediaPlayer = MediaPlayer.create(context, R.raw.hand_clapping)
        mediaPlayer.start()

        confetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(5000L)
            .addShapes(
                nl.dionsegijn.konfetti.models.Shape.Square,
                nl.dionsegijn.konfetti.models.Shape.CIRCLE)
            .addSizes(nl.dionsegijn.konfetti.models.Size(12))
            .setPosition(0f, confetti.width + 50f, -50f, -50f)
            .streamFor(300, 8000L)

    }

    private fun vibrate() {
        val vibrator = confetti.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val pattern = longArrayOf(100, 100, 100, 1000, 500, 100, 300, 100, 200, 200,
            200,200,200,100,100,200,100,100,200,100,200,100,100,200,200,100,200,300,300,300)

        vibrator.vibrate(pattern, -1)
    }
}