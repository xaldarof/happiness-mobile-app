package pdf.reader.happiness.tools

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import pdf.reader.happiness.R

interface RestartDialog {

    fun show(context: Context)

    class Base : RestartDialog {
        override fun show(context: Context) {
            val dialog = BottomSheetDialog(context,R.style.BottomSheetDialogTheme)
                dialog.setContentView(R.layout.should_restart)
            dialog.setOnCancelListener {
                dialog.show()
            }
            dialog.show()
        }
    }
}