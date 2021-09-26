package pdf.reader.happiness.tools

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView

interface TypeFaceController {
    fun change(textView: TextView)

    class Base(private val context: Context): TypeFaceController {
        override fun change(textView: TextView) {
            val face = Typeface.createFromAsset(context.assets, "fonts/new_font.ttf")
             textView.typeface = face
        }
    }
}