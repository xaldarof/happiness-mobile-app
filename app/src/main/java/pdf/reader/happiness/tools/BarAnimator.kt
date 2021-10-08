package pdf.reader.happiness.tools

import android.view.View

interface BarAnimator {

    fun check()

    class Base(private val view:View) :
        BarAnimator {

        var isOpen: Boolean = true

        override fun check() {
            if (isOpen) {
                view.animate().translationY(-200F)
                isOpen = false

            } else {
                view.animate().translationY(0F)
                isOpen = true
            }
        }
    }
}