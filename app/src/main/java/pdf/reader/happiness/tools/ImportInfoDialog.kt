package pdf.reader.happiness.tools

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pdf.reader.happiness.R

interface ImportInfoDialog {

    fun show(context: Context)

    fun showInfoAboutPublish(context: Context,callBack: CallBack)

    fun showClearText(context: Context,callBack: CallBack)

    class Base : ImportInfoDialog {
        override fun show(context: Context) {
            val alertDialog = MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                .setMessage(R.string.what_is_it)
                .setTitle("Информация о Внедрение данных из облака. (бета)")
                .setPositiveButton(
                    "ок"
                ) { p0, p1 -> }
            alertDialog.show()

        }

        override fun showInfoAboutPublish(context: Context,callBack: CallBack) {
            val alertDialog = MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                .setMessage(R.string.when_my_publish)
                .setTitle("Когда моя публикация будет доступен для других ?")
                .setPositiveButton(
                    "Понятно"
                ) { p0, p1 -> }

            alertDialog.show()


        }

        override fun showClearText(context: Context,callBack: CallBack) {
            val alertDialog = MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                .setMessage("Очистить текст ?")
                .setPositiveButton(
                    "Да"
                ) { p0, p1 ->
                    callBack.onClickClear()
                }
                .setNegativeButton(
                    "Нет"
                ) { p0, p1 ->

                }

            alertDialog.show()
        }
    }
    interface CallBack {
        fun onClickClear()
    }
}