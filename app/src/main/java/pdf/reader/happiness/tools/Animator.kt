package pdf.reader.happiness.tools

import android.view.View
import render.animations.Attention
import render.animations.Render


fun View.animation() {
    val render = Render(context)
    render.setDuration(10000)
    render.setAnimation(Attention.Pulse(this))
    render.start()
    this.animate().rotation(360f).setDuration(20000).start()

}

fun View.pulseAnimation(duration: Long) {
    val render = Render(context)
    render.setDuration(duration)
    render.setAnimation(Attention.Pulse(this))
    render.start()
}

