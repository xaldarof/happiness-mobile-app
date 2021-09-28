package pdf.reader.happiness.tools

import android.text.Editable
import android.text.TextWatcher

interface TextWatcherCallBack {

    interface CustomTextWatcher{
        fun onTextChange(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
    }

    class Base(private val customTextWatcher: CustomTextWatcher): TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            customTextWatcher.onTextChange(p0,p1,p2,p3)
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }
}