package pdf.reader.happiness.tools

import android.view.View
import pdf.reader.happiness.core.InfoModel


fun View.showIfEmpty(list: List<InfoModel>) {
    if (list.isNotEmpty()) {
        this.visibility = View.INVISIBLE
    }else {
        this.visibility = View.VISIBLE
    }
}

