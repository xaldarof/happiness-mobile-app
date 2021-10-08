package pdf.reader.happiness.tools

import android.content.Context
import androidx.appcompat.app.AlertDialog

interface ReadingWarningDialog {

    fun show(context: Context)

    class Base(private val dialogCallBack: DialogCallBack): ReadingWarningDialog {
        override fun show(context: Context) {
            val alertDialog = AlertDialog.Builder(context)
                .setTitle("Внимание")
                .setMessage("Отметить как прочитанный ?")
                .setPositiveButton("Да") { p0, p1 ->
                    p0.dismiss()
                    dialogCallBack.updateCallBack(true)
                }
                .setNegativeButton("Нет") { p0, p1 ->
                    p0.dismiss()
                    dialogCallBack.exitCallBack()
                }
            alertDialog.show()
        }
    }
    interface DialogCallBack{
        fun updateCallBack(state:Boolean)
        fun exitCallBack()
    }
}