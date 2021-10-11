package pdf.reader.happiness.tools

import android.view.View
import android.widget.AdapterView

interface CallBackSpinner {
    fun onSelect(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
}

class SpinnerClickCallBack(private val callBack: CallBackSpinner): AdapterView.OnItemSelectedListener {
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        callBack.onSelect(p0,p1,p2,p3)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}