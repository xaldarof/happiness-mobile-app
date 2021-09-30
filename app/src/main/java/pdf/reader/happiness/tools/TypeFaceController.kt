package pdf.reader.happiness.tools

import android.content.Context
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import pdf.reader.happiness.R

interface TypeFaceController {

    fun changeToBold(textView: TextView)
    fun changeToDef(textView: TextView)

    class Base(private val context: Context) : TypeFaceController {
        override fun changeToBold(textView: TextView) {
            change(R.font.roboto_medium, textView)
        }

        override fun changeToDef(textView: TextView) {
            change(R.font.light, textView)
        }

        private fun change(@FontRes id:Int, textView: TextView) {
            val typeface = ResourcesCompat.getFont(context, id)
            textView.typeface = typeface
        }
    }
}