package pdf.reader.happiness.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import pdf.reader.happiness.data.models.InfoModel

class DiffUtill(private val oldList:List<InfoModel>, private val newList: List<InfoModel>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldInfo = oldList[oldItemPosition]
        val newInfo = newList[newItemPosition]
        return oldInfo.body == newInfo.body
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldInfo = oldList[oldItemPosition]
        val newInfo = newList[newItemPosition]

        return oldInfo.favorite == newInfo.favorite &&
                oldInfo.finished == newInfo.finished &&
                oldInfo.body == newInfo.body &&
                oldInfo.title == newInfo.title
    }
}