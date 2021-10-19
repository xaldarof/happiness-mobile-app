package pdf.reader.happiness.data.cache.settings_cache

import android.content.Context
import androidx.appcompat.app.AlertDialog

interface ClearDialog {

    fun show(context: Context)

    class Base(private val clearDialogCallBack: ClearDialogCallBack): ClearDialog {
        override fun show(context: Context) {
            val alertDialog = AlertDialog.Builder(context)
                .setTitle("Внимание")
                .setMessage("Вы действительно хотите обнулить все данные ?")
                .setPositiveButton("Да") { p0, p1 ->
                    p0.dismiss()
                    clearDialogCallBack.onClickYes()
                }
                .setNegativeButton("Нет") { p0, p1 ->
                    p0.dismiss()
                }
            alertDialog.show()
        }
    }
    interface ClearDialogCallBack{
        fun onClickYes()
    }
}