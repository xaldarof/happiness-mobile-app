package pdf.reader.happiness.tools

import android.content.Context
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pdf.reader.happiness.R
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.data.cloud.data_insert.TokenIdGenerator
import pdf.reader.happiness.databinding.CreateTokenBinding
import pdf.reader.happiness.databinding.TokenInfoDialogBinding

interface TokenDialog {

    fun show(tokenModel: TokenModel, callback: TokenDialog.CallBack)
    fun showInfo()
    fun showCreateTokenDialog(userBalance: Int, callback: CallBack)

    class Base(private val context: Context) : TokenDialog {

        override fun show(tokenModel: TokenModel, callback: TokenDialog.CallBack) {

            val dialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
            val binding = TokenInfoDialogBinding.inflate(dialog.layoutInflater)
            dialog.setContentView(binding.root)

            binding.tokenDate.text = tokenModel.tokenDate.toLong().formatToDate()
            binding.tokenValue.text = tokenModel.tokenValue.toString()
            binding.tokenId.text = "${tokenModel.tokenId.substring(0, 8)}*****"

            binding.activeBtn.setOnClickListener {
                callback.onClickActive()
                Toast.makeText(
                    context,
                    "Поздравляю, вы успешно активировали токен !",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }

            dialog.show()
        }

        override fun showInfo() {
            val alertDialog = MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                .setMessage(R.string.token_info)
                .setTitle("Информация о токенах")
                .setPositiveButton(
                    "ок"
                ) { p0, p1 -> }
            alertDialog.show()
        }


        override fun showCreateTokenDialog(userBalance: Int, callback: CallBack) {
            val dialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
            val binding = CreateTokenBinding.inflate(dialog.layoutInflater)
            val connectionManager = ConnectionManager(context)

            dialog.setContentView(binding.root)

            val randomId = TokenIdGenerator().getGeneratedId()
            binding.tokenDate.text = (System.currentTimeMillis() / 1000).formatToDate()
            binding.tokenId.text = "${randomId.substring(0, 8)}*****"
            binding.balance.text = userBalance.toString()


            binding.createBtn.setOnClickListener {

                val userEnteredCount = binding.tokenValue.text.toString()

                if (userEnteredCount.isNotEmpty()) {
                    if (connectionManager.isConnected()) {
                        if (userEnteredCount.toInt() != 0) {
                            if (userBalance < userEnteredCount.toInt()) {
                                Toast.makeText(
                                    context,
                                    R.string.not_enough_money,
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                callback.onClickCreate(randomId, userEnteredCount.toInt())
                                dialog.dismiss()
                                Toast.makeText(context, "Токен успешно создан !", Toast.LENGTH_LONG)
                                    .show()
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Нельзя создать токен с нулевым количеством",
                                Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, R.string.no_connection, Toast.LENGTH_LONG)
                            .show()
                    }
                } else {
                    binding.relativeLayout1.errorAnimation()
                }
            }


            dialog.show()
        }
    }


    interface CallBack {
        fun onClickActive()
        fun onClickCreate(id: String, userEnteredCount: Int)
    }
}