package pdf.reader.happiness.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import pdf.reader.happiness.core.ChapterModel


class ChapterDiffUtilCallBack(
    private val oldList: List<ChapterModel>,
    private val newList: List<ChapterModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldInfo = oldList[oldItemPosition]
        val newInfo = newList[newItemPosition]
        return oldInfo.name == newInfo.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldInfo = oldList[oldItemPosition]
        val newInfo = newList[newItemPosition]

        return oldInfo.name == newInfo.name &&
                oldInfo.size == newInfo.size &&
                oldInfo.image == newInfo.image &&
                oldInfo.progress == newInfo.progress &&
                oldInfo.isFinished == newInfo.isFinished &&
                oldInfo.isCongratulated == newInfo.isCongratulated &&
                oldInfo.fragmentName == newInfo.fragmentName
    }
}