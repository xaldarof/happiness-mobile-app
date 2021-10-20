package pdf.reader.happiness.tools

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun String.copyToClipBoard(context: Context){
    val clipboard: ClipboardManager? = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val clip = ClipData.newPlainText("text", this)
    clipboard?.setPrimaryClip(clip)
}