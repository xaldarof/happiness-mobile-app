package pdf.reader.happiness.presentation.adapter

import android.R
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.databinding.ChapterItemBinding


class ChapterItemAdapter(private val onClick: OnClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<ChapterModel>()
    private var lastPosition = -1

    fun update(newList: List<ChapterModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private inner class ItemViewHolder(private val chapterItemBinding: ChapterItemBinding) :
        RecyclerView.ViewHolder(chapterItemBinding.root) {
        @SuppressLint("SetTextI18n")

        fun onBind(chapter: ChapterModel) {
            chapterItemBinding.chapterIcon.setImageResource(chapter.image)
            chapterItemBinding.chapterName.text = chapter.name
            chapterItemBinding.chapterSize.text = "${chapter.size} Советов"
            chapterItemBinding.chapterProgress.progress = chapter.progress
            chapterItemBinding.chapterProgress.setEndProgress(chapter.progress)
            chapterItemBinding.chapterProgress.startProgressAnimation()

            chapterItemBinding.chapterBtn.setOnClickListener { onClick.onClick(chapter) }

            if (chapter.isFinished){
                chapterItemBinding.finishedIcon.visibility = View.VISIBLE
            }else{
                chapterItemBinding.finishedIcon.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ChapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).onBind(list[position])
        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_in_left)
            animation.duration = 800
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    interface OnClick {
        fun onClick(chapter: ChapterModel)
    }
}