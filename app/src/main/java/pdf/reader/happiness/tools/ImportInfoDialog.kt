package pdf.reader.happiness.tools

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pdf.reader.happiness.R

interface ImportInfoDialog {

    fun show(context: Context)

    class Base : ImportInfoDialog {
        override fun show(context: Context) {
            val alertDialog = MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                .setMessage(R.string.what_is_it)
                .setTitle("Информация о 'Внедрение данных из облака'(бета)")
                .setPositiveButton(
                    "Понятно"
                ) { p0, p1 -> }
            alertDialog.show()

        }
    }
}