package pdf.reader.happiness.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import pdf.reader.happiness.core.TokenModel

class TokenHistoryDiffUtilCallBack(
    private val oldList: List<TokenModel>,
    private val newList: List<TokenModel>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.tokenId == newItem.tokenId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.tokenId == newItem.tokenId &&
                oldItem.tokenDate == newItem.tokenDate &&
                oldItem.tokenValue == newItem.tokenValue
    }
}