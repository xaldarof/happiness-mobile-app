package pdf.reader.happiness.tools

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import pdf.reader.happiness.R
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.databinding.TokenInfoDialogBinding

interface TokenDialog {

    fun show(tokenModel: TokenModel,callback:TokenDialog.CallBack)

    class Base(private val context: Context): TokenDialog {

        override fun show(tokenModel: TokenModel,callback:TokenDialog.CallBack) {

            val dialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
            val binding = TokenInfoDialogBinding.inflate(dialog.layoutInflater)
            dialog.setContentView(binding.root)

            binding.tokenDate.text = tokenModel.tokenDate.toLong().formatToDate()
            binding.tokenValue.text = tokenModel.tokenValue.toString()
            binding.tokenId.text = "${tokenModel.tokenId.substring(0,8)}*****"

            binding.activeBtn.setOnClickListener {
                callback.onClickActive()
            }

            dialog.show()
        }
    }
    interface CallBack{
        fun onClickActive()
    }
}