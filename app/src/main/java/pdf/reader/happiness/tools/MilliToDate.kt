package pdf.reader.happiness.tools

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Long.formatToDate():String {
    return SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date (this*1000))
}