package pdf.reader.happiness.tools

import java.text.SimpleDateFormat
import java.util.*


class DateFormatter {

    fun getFormattedDateTime(long: Long): String = SimpleDateFormat("HH:mm").format(Date(long))
}