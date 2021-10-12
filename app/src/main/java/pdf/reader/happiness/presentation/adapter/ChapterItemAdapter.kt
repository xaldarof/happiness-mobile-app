package pdf.reader.happiness.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import pdf.reader.happiness.R
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.core.Name
import pdf.reader.happiness.data.cache.settings_cache.ThemeController
import pdf.reader.happiness.databinding.ChapterItemBinding


class ChapterItemAdapter(private val onClick: OnClick,private val themeController: ThemeController) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<ChapterModel>()
    private var lastPosition = -1

    fun update(newList: List<ChapterModel>) {
        val diffUtil = ChapterDiffUtilCallBack(list,newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    private inner class ItemViewHolder(private val chapterItemBinding: ChapterItemBinding) :
        RecyclerView.ViewHolder(chapterItemBinding.root) {
        @SuppressLint("SetTextI18n")

        fun onBind(chapter: ChapterModel) {
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

            if (chapter.name== Name.SUCCESS) chapterItemBinding.chapterIcon.setImageResource(R.drawable.ic_goal)
            if (chapter.name== Name.LIFE) chapterItemBinding.chapterIcon.setImageResource(R.drawable.ic_goldfish)
            if (chapter.name== Name.LOVE) chapterItemBinding.chapterIcon.setImageResource(R.drawable.ic_talk)
            if (chapter.name== Name.HAPPY) chapterItemBinding.chapterIcon.setImageResource(R.drawable.ic_diagram)
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
                AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_in_left_custom)
            animation.duration = 800
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    interface OnClick {
        fun onClick(chapter: ChapterModel)
    }
}