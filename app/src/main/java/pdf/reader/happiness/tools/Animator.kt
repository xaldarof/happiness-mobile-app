package pdf.reader.happiness.tools

import android.content.Context
import android.view.View
import render.animations.Attention
import render.animations.Render

class Animator(private val context: Context){

    fun animation(view:View,duration:Long) {
        val render = Render(context)
        render.setDuration(duration)
        render.setAnimation(Attention.Pulse(view))
        render.start()

    }
}