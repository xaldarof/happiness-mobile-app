package pdf.reader.happiness.tools

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.annotation.RequiresApi




@RequiresApi(Build.VERSION_CODES.M)
fun Activity.hideUi() {
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity.showUi() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

