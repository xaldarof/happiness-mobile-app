package pdf.reader.happiness.tools

import android.graphics.Color
import nl.dionsegijn.konfetti.KonfettiView

class CongratulationView(private val confetti: KonfettiView) {

    fun show() {
        confetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(5000L)
            .addShapes(
                nl.dionsegijn.konfetti.models.Shape.Square,
                nl.dionsegijn.konfetti.models.Shape.CIRCLE
            )
            .addSizes(nl.dionsegijn.konfetti.models.Size(12))
            .setPosition(0f, confetti.width + 50f, -50f, -50f)
            .streamFor(300, 10000L)
    }
}