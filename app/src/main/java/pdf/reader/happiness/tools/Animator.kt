package pdf.reader.happiness.tools

import android.view.View
import render.animations.Attention
import render.animations.Render
import render.animations.Zoom


fun View.errorAnimation() {
    val render = Render(context)
    render.setDuration(1000)
    render.setAnimation(Attention.Shake(this))
    render.start()
}

fun View.inAnimation() {
    val render = Render(context)
    render.setDuration(1000)
    render.setAnimation(Attention.RuberBand(this))
    render.start()
}

fun View.pulseAnimation(duration: Long) {
    val render = Render(context)
    render.setDuration(duration)
    render.setAnimation(Attention.Pulse(this))
    render.start()
}

