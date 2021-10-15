package pdf.reader.happiness.tools

import android.text.Editable
import android.text.TextWatcher

interface CallBack{
    fun onChange(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
}
class MyTextWatcher(private val callback: CallBack):TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        callback.onChange(p0,p1,p2, p3)
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}